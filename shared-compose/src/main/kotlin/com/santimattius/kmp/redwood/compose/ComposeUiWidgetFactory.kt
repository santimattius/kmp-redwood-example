package com.santimattius.kmp.redwood.compose

import androidx.compose.runtime.Composable
import com.santimattius.kmp.redwood.example.widget.Button
import com.santimattius.kmp.redwood.example.widget.SchemaWidgetFactory
import com.santimattius.kmp.redwood.example.widget.Text

object ComposeUiWidgetFactory : SchemaWidgetFactory<@Composable () -> Unit> {
    override fun Text(): Text<@Composable () -> Unit> = ComposeUiText()
    override fun Button(): Button<@Composable () -> Unit> = ComposeUiButton()
}