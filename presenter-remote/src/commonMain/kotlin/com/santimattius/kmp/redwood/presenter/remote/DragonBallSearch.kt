package com.santimattius.kmp.redwood.presenter.remote

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import app.cash.redwood.Modifier
import app.cash.redwood.compose.LocalUiConfiguration
import app.cash.redwood.layout.api.Constraint
import app.cash.redwood.layout.api.CrossAxisAlignment
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Row
import app.cash.redwood.layout.compose.Spacer
import app.cash.redwood.lazylayout.compose.ExperimentalRedwoodLazyLayoutApi
import app.cash.redwood.lazylayout.compose.LazyColumn
import app.cash.redwood.lazylayout.compose.items
import app.cash.redwood.lazylayout.compose.rememberLazyListState
import app.cash.redwood.ui.Margin
import app.cash.redwood.ui.dp
import com.santimattius.kmp.redwood.example.compose.Image
import com.santimattius.kmp.redwood.example.compose.Text
import com.santimattius.kmp.redwood.example.compose.TextInput
import com.santimattius.kmp.redwood.example.compose.reuse
import com.santimattius.kmp.redwood.values.TextFieldState
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlin.random.Random

data class CharacterImage(
    val id: Long,
    val name: String,
    val url: String,
    val description: String = ""
)

// TODO Switch to https://github.com/cashapp/zipline/issues/490 once available.
@Suppress("FUN_INTERFACE_WITH_SUSPEND_FUNCTION") // https://youtrack.jetbrains.com/issue/KTIJ-7642
fun interface HttpClient {
    suspend fun call(url: String, headers: Map<String, String>): String
}

interface Navigator {
    /** Open a URL in the app that owns it. For example, a browser. */
    fun openUrl(url: String)
}

@Composable
@OptIn(ExperimentalRedwoodLazyLayoutApi::class)
fun DragonBallSearch(
    httpClient: HttpClient,
    navigator: Navigator,
    modifier: Modifier = Modifier,
    viewInsets: Margin = LocalUiConfiguration.current.safeAreaInsets,
) {
    val scope = rememberCoroutineScope()
    val characters = remember { mutableStateListOf<CharacterImage>() }

    // Simple counter that allows us to trigger refreshes by simple incrementing the value
    var refreshSignal by remember { mutableIntStateOf(0) }
    var refreshing by remember { mutableStateOf(false) }

    val searchTermSaver = object : Saver<TextFieldState, String> {
        override fun restore(value: String) = TextFieldState(value)
        override fun SaverScope.save(value: TextFieldState) = value.text
    }

    var searchTerm by rememberSaveable(stateSaver = searchTermSaver) {
        mutableStateOf(
            TextFieldState(
                ""
            )
        )
    }

    val lazyListState = rememberLazyListState()

    LaunchedEffect(searchTerm) {
        lazyListState.programmaticScroll(0, animated = true)
    }

    LaunchedEffect(refreshSignal) {
        try {
            //refreshing = true
            val jsonResponse = httpClient.call(
                url = "https://dragonball-api.com/api/characters",
                headers = emptyMap(),
            )
            val json = Json {
                allowStructuredMapKeys = true
                ignoreUnknownKeys = true
            }
            val response = json.decodeFromString(
                CharacterResponse.serializer(),
                jsonResponse
            )

            characters.clear()
            characters.addAll(response.items.map { item ->
                CharacterImage(
                    item.id,
                    item.name,
                    item.image,
                    description = item.race
                )
            })
        } finally {
            //refreshing = false
        }
    }

    val filteredEmojis by remember {
        derivedStateOf {
            val searchTerms = searchTerm.text.split(" ")
            characters.filter { image ->
                searchTerms.all { image.name.contains(it, ignoreCase = true) }
            }
        }
    }

    Column(
        width = Constraint.Fill,
        height = Constraint.Fill,
        horizontalAlignment = CrossAxisAlignment.Stretch,
        margin = Margin(start = viewInsets.start, end = viewInsets.end, top = viewInsets.top),
        modifier = modifier,
    ) {
        TextInput(
            state = TextFieldState(searchTerm.text),
            hint = "Search",
            onChange = { textFieldState ->
                // Make it easy to trigger a crash to manually test exception handling!
                when (textFieldState.text) {
                    "crash" -> throw RuntimeException("boom!")

                    "async" -> {
                        scope.launch {
                            throw RuntimeException("boom!")
                        }
                    }
                }

                searchTerm = textFieldState
            },
        )

        LazyColumn(
            refreshing = refreshing,
            onRefresh = { refreshSignal++ },
            state = lazyListState,
            width = Constraint.Fill,
            modifier = Modifier.flex(1.0),
            placeholder = {
                Item(
                    characterImage = loadingCharacterImage,
                    onClick = {},
                )
            },
        ) {
            items(filteredEmojis) { image ->
                Item(
                    modifier = Modifier.reuse(),
                    characterImage = image,
                    onClick = {
                        navigator.openUrl(image.url)
                    },
                )
            }
            item {
                Spacer(height = viewInsets.bottom)
            }
        }
    }
}

@Composable
fun Item(
    characterImage: CharacterImage,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        width = Constraint.Fill,
        height = Constraint.Wrap,
        verticalAlignment = CrossAxisAlignment.Center,
        horizontalAlignment = MainAxisAlignment.Start,
    ) {
        Image(
            url = characterImage.url,
            modifier = Modifier
                .margin(Margin(8.dp))
                .size(64.dp, 64.dp),
            onClick = onClick,
        )

        Text(text = characterImage.name.uppercase())
    }
}

val loadingCharacterImage = CharacterImage(
    id = Random.nextLong(),
    name = "loading…",
    url = "",
)
