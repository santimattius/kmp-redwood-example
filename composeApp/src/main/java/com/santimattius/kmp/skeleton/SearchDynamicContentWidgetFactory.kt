package com.santimattius.kmp.skeleton

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.cash.redwood.Modifier as RedwoodModifier
import app.cash.redwood.treehouse.Crashed
import app.cash.redwood.treehouse.DynamicContentWidgetFactory
import app.cash.redwood.treehouse.Loading

internal class SearchDynamicContentWidgetFactory : DynamicContentWidgetFactory<@Composable () -> Unit> {

    override fun Loading() = RealLoading()

    override fun Crashed() = RealCrashed()

    internal class RealLoading : Loading<@Composable () -> Unit> {
        override var modifier: RedwoodModifier = RedwoodModifier
        override val value = @Composable {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                BasicText("loading...")
            }
        }
    }

    internal class RealCrashed : Crashed<@Composable () -> Unit> {
        private var uncaughtException by mutableStateOf<Throwable?>(null)

        override var modifier: RedwoodModifier = RedwoodModifier
        override val value = @Composable {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                BasicText(uncaughtException?.stackTraceToString() ?: "")
            }
        }

        override fun uncaughtException(uncaughtException: Throwable) {
            this.uncaughtException = uncaughtException
        }

        override fun restart(restart: () -> Unit) {
        }
    }
}