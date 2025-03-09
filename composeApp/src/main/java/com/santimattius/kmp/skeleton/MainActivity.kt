package com.santimattius.kmp.skeleton

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.cash.redwood.layout.composeui.ComposeUiRedwoodLayoutWidgetFactory
import app.cash.redwood.lazylayout.composeui.ComposeUiRedwoodLazyLayoutWidgetFactory
import app.cash.redwood.leaks.LeakDetector
import app.cash.redwood.leaks.RedwoodLeakApi
import app.cash.redwood.treehouse.EventListener
import app.cash.redwood.treehouse.TreehouseApp
import app.cash.redwood.treehouse.TreehouseAppFactory
import app.cash.redwood.treehouse.TreehouseContentSource
import app.cash.redwood.treehouse.TreehouseView
import app.cash.redwood.treehouse.composeui.TreehouseContent
import app.cash.zipline.Zipline
import app.cash.zipline.ZiplineManifest
import app.cash.zipline.loader.ManifestVerifier
import app.cash.zipline.loader.asZiplineHttpClient
import app.cash.zipline.loader.withDevelopmentServerPush
import coil3.ImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.serviceLoaderEnabled
import com.santimattius.kmp.redwood.compose.ComposeUiWidgetFactory
import com.santimattius.kmp.redwood.example.protocol.host.DragonBallProtocolFactory
import com.santimattius.kmp.redwood.example.widget.DragonBallWidgetSystem
import com.santimattius.kmp.redwood.launcher.AppSpec
import com.santimattius.kmp.redwood.treehouse.SearchPresenter
import com.santimattius.kmp.skeleton.ui.theme.KMPGradleSkeletonTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okio.Path.Companion.toPath
import okio.assetfilesystem.asFileSystem
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource

@OptIn(RedwoodLeakApi::class)
class MainActivity : ComponentActivity() {

    private val scope: CoroutineScope = CoroutineScope(Main)
    private val hostState = SnackbarHostState()

    private val leakDetector = LeakDetector.timeBasedIn(
        scope = scope,
        timeSource = TimeSource.Monotonic,
        leakThreshold = 10.seconds,
        callback = { reference, note ->
            Log.e("LEAK", "Leak detected! $reference $note")
        },
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val client = OkHttpClient()
        val treehouseApp = createTreehouseApp(client)
        val treehouseContentSource = TreehouseContentSource(SearchPresenter::launch)

        val imageLoader = ImageLoader.Builder(this)
            .serviceLoaderEnabled(false)
            .components {
                add(OkHttpNetworkFetcherFactory(client))
            }
            .build()

        val widgetSystem = TreehouseView.WidgetSystem { json, protocolMismatchHandler ->
            DragonBallProtocolFactory<@Composable () -> Unit>(
                widgetSystem = DragonBallWidgetSystem(
                    DragonBall = ComposeUiWidgetFactory(imageLoader),
                    RedwoodLayout = ComposeUiRedwoodLayoutWidgetFactory(),
                    RedwoodLazyLayout = ComposeUiRedwoodLazyLayoutWidgetFactory(),
                ),
                json = json,
                mismatchHandler = protocolMismatchHandler,
            )
        }

        setContent {
            KMPGradleSkeletonTheme {
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState) },
                ) { contentPadding ->
                    TreehouseContent(
                        treehouseApp = treehouseApp,
                        widgetSystem = widgetSystem,
                        contentSource = treehouseContentSource,
                        modifier = Modifier.padding(contentPadding),
                        dynamicContentWidgetFactory = SearchDynamicContentWidgetFactory(),
                    )
                }
            }
        }
    }

    val appEventListener: EventListener = object : EventListener() {
        private var success = true
        private var snackbarJob: Job? = null

        override fun codeLoadFailed(exception: Exception, startValue: Any?) {
            Log.w("Treehouse", "codeLoadFailed", exception)
            if (success) {
                // Only show the Snackbar on the first transition from success.
                success = false
                snackbarJob = scope.launch {
                    hostState.showSnackbar(
                        message = "Unable to load guest code from server",
                        actionLabel = "Dismiss",
                        duration = SnackbarDuration.Indefinite,
                    )
                }
            }
        }

        override fun codeLoadSuccess(
            manifest: ZiplineManifest,
            zipline: Zipline,
            startValue: Any?
        ) {
            Log.i("Treehouse", "codeLoadSuccess")
            success = true
            snackbarJob?.cancel()
        }
    }

    private fun createTreehouseApp(httpClient: OkHttpClient): TreehouseApp<SearchPresenter> {
        val treehouseAppFactory = TreehouseAppFactory(
            context = applicationContext,
            httpClient = httpClient,
            manifestVerifier = ManifestVerifier.Companion.NO_SIGNATURE_CHECKS,
            embeddedFileSystem = applicationContext.assets.asFileSystem(),
            embeddedDir = "/".toPath(),
            leakDetector = leakDetector,
        )

        val manifestUrlFlow = flowOf("http://10.0.2.2:8080/manifest.zipline.json")
            .withDevelopmentServerPush(httpClient.asZiplineHttpClient())

        val treehouseApp = treehouseAppFactory.create(
            appScope = scope,
            spec = AppSpec(
                manifestUrl = manifestUrlFlow,
                hostApi = RealHostApi(
                    client = httpClient,
                    openUrl = { url ->
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.setData(Uri.parse(url))
                        startActivity(intent)
                    },
                ),
            ),
            eventListenerFactory = object : EventListener.Factory {
                override fun create(app: TreehouseApp<*>, manifestUrl: String?) = appEventListener
                override fun close() {}
            },
        )

        treehouseApp.start()

        return treehouseApp
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KMPGradleSkeletonTheme {
        //Greeting("Android")
    }
}