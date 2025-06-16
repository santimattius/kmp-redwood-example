package com.santimattius.kmp.redwood.compose

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import com.santimattius.kmp.redwood.example.modifier.Reuse
import com.santimattius.kmp.redwood.example.widget.DragonBallWidgetFactory
import com.santimattius.kmp.redwood.example.widget.Image
import com.santimattius.kmp.redwood.example.widget.Text
import com.santimattius.kmp.redwood.example.widget.TextInput

class ComposeUiWidgetFactory(
    private val imageLoader: ImageLoader,
) : DragonBallWidgetFactory<@Composable () -> Unit> {
    override fun TextInput(): TextInput<@Composable () -> Unit> = ComposeUiTextInput()
    override fun Text(): Text<@Composable () -> Unit> = ComposeUiText()
    override fun Image(): Image<@Composable () -> Unit> = ComposeUiImage(imageLoader)
    override fun Reuse(value: @Composable () -> Unit, modifier: Reuse) {
    }
}