package com.santimattius.kmp.redwood.presenter.remote

import androidx.compose.runtime.Composable
import app.cash.redwood.compose.ConsumeInsets
import app.cash.redwood.treehouse.TreehouseUi

class DragonBallSearchTreehouseUi(
  private val httpClient: HttpClient,
  private val navigator: Navigator,
) : TreehouseUi {
  @Composable
  override fun Show() {
    ConsumeInsets { insets ->
      DragonBallSearch(
        httpClient = httpClient,
        navigator = navigator,
        viewInsets = insets,
      )
    }
  }
}