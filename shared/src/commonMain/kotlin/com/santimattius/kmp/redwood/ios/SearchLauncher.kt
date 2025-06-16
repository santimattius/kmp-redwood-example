package com.santimattius.kmp.redwood.ios
import app.cash.redwood.leaks.LeakDetector
import app.cash.redwood.leaks.RedwoodLeakApi
import app.cash.redwood.treehouse.EventListener
import app.cash.redwood.treehouse.TreehouseApp
import app.cash.redwood.treehouse.TreehouseAppFactory
import app.cash.zipline.Zipline
import app.cash.zipline.ZiplineManifest
import app.cash.zipline.loader.ManifestVerifier
import app.cash.zipline.loader.asZiplineHttpClient
import app.cash.zipline.loader.withDevelopmentServerPush
import com.santimattius.kmp.redwood.launcher.AppSpec
import com.santimattius.kmp.redwood.treehouse.HostApi
import com.santimattius.kmp.redwood.treehouse.SearchPresenter

import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.flowOf
import platform.Foundation.NSLog
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSURLSession

class SearchLauncher(
    private val nsurlSession: NSURLSession,
    private val hostApi: HostApi,
) {
    private val coroutineScope: CoroutineScope = MainScope()
    private val manifestUrl = "http://localhost:8080/manifest.zipline.json"

    @OptIn(RedwoodLeakApi::class)
    @Suppress("unused") // Invoked in Swift.
    fun createTreehouseApp(listener: EmojiSearchEventListener): TreehouseApp<SearchPresenter> {
        val ziplineHttpClient = nsurlSession.asZiplineHttpClient()

        val eventListener = object : EventListener() {
            override fun codeLoadFailed(exception: Exception, startValue: Any?) {
                NSLog("Treehouse: codeLoadFailed: $exception")
                NSOperationQueue.mainQueue.addOperationWithBlock {
                    listener.codeLoadFailed()
                }
            }

            override fun codeLoadSuccess(manifest: ZiplineManifest, zipline: Zipline, startValue: Any?) {
                NSLog("Treehouse: codeLoadSuccess")
                NSOperationQueue.mainQueue.addOperationWithBlock {
                    listener.codeLoadSuccess()
                }
            }
        }

        val treehouseAppFactory = TreehouseAppFactory(
            httpClient = ziplineHttpClient,
            manifestVerifier = ManifestVerifier.Companion.NO_SIGNATURE_CHECKS,
            leakDetector = LeakDetector.timeBasedIn(
                scope = coroutineScope,
                timeSource = TimeSource.Monotonic,
                leakThreshold = 10.seconds,
                callback = { reference, note ->
                    NSLog("Leak detected! $reference $note")
                },
            ),
        )

        val manifestUrlFlow = flowOf(manifestUrl)
            .withDevelopmentServerPush(ziplineHttpClient)

        val treehouseApp = treehouseAppFactory.create(
            appScope = coroutineScope,
            spec = AppSpec(
                manifestUrl = manifestUrlFlow,
                hostApi = hostApi,
            ),
            eventListenerFactory = object : EventListener.Factory {
                override fun create(app: TreehouseApp<*>, manifestUrl: String?) = eventListener
                override fun close() {
                }
            },
        )

        treehouseApp.start()

        return treehouseApp
    }
}

interface EmojiSearchEventListener {
    fun codeLoadFailed()
    fun codeLoadSuccess()
}
