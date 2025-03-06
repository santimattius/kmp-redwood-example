package com.santimattius.kmp.redwood.compose

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.santimattius.kmp.redwood.example.widget.Button
import app.cash.redwood.Modifier as RedwoodModifier

internal class ComposeUiButton : Button<@Composable () -> Unit> {
    private var text by mutableStateOf("")
    private var isEnabled by mutableStateOf(false)
    private var onClick by mutableStateOf({})

    override var modifier: RedwoodModifier = RedwoodModifier

    override val value = @Composable {
        Button(
            onClick = onClick,
            enabled = isEnabled,
            modifier = Modifier,
        ) {
            Text(text)
        }
    }

    override fun text(text: String?) {
        this.text = text ?: ""
    }

    override fun enabled(enabled: Boolean) {
        this.isEnabled = enabled
    }

    override fun onClick(onClick: (() -> Unit)?) {
        this.onClick = onClick ?: {}
    }
}
