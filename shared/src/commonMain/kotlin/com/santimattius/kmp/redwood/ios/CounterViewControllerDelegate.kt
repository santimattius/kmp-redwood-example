package com.santimattius.kmp.redwood.ios

import app.cash.redwood.compose.DisplayLinkClock
import app.cash.redwood.compose.RedwoodComposition
import app.cash.redwood.layout.uiview.UIViewRedwoodLayoutWidgetFactory
import app.cash.redwood.widget.RedwoodUIView
import com.santimattius.kmp.redwood.example.widget.SchemaWidgetSystem
import com.santimattius.kmp.redwood.presenter.Counter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.plus

@Suppress("unused") // Called from Swift.
class CounterViewControllerDelegate(
    redwoodUIView: RedwoodUIView,
) {
    private val scope = MainScope() + DisplayLinkClock

    init {
        val composition = RedwoodComposition(
            scope = scope,
            view = redwoodUIView,
            widgetSystem = SchemaWidgetSystem(
                Schema = IosWidgetFactory,
                RedwoodLayout = UIViewRedwoodLayoutWidgetFactory(),
            ),
        )
        composition.setContent {
            Counter()
        }
    }

    fun dispose() {
        scope.cancel()
    }
}