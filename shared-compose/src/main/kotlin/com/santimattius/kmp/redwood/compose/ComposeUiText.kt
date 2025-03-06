package com.santimattius.kmp.redwood.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import app.cash.redwood.Modifier
import com.santimattius.kmp.redwood.example.widget.Text

internal class ComposeUiText : Text<@Composable () -> Unit> {
    private var text by mutableStateOf("")

    override var modifier: Modifier = Modifier

    override val value = @Composable {
        Text(
            text = text,
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground,
        )
    }

    override fun text(text: String?) {
        this.text = text ?: ""
    }
}
