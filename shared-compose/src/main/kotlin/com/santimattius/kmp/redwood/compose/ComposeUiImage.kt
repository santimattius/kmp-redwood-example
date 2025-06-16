package com.santimattius.kmp.redwood.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import com.santimattius.kmp.redwood.example.widget.Image
import app.cash.redwood.Modifier as RedwoodModifier

internal class ComposeUiImage(
    private val imageLoader: ImageLoader,
) : Image<@Composable () -> Unit> {

    private var url by mutableStateOf("")
    private var onClick by mutableStateOf({})

    override var modifier: RedwoodModifier = RedwoodModifier

    override val value = @Composable {
        AsyncImage(
            model = url,
            imageLoader = imageLoader,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .background(Color.White)
                .size(64.dp)
                .clickable(onClick = onClick),
        )
    }

    override fun url(url: String) {
        this.url = url
    }

    override fun onClick(onClick: (() -> Unit)?) {
        this.onClick = onClick ?: {}
    }
}