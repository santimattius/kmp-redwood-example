package com.santimattius.kmp.redwood.treehouse

import app.cash.zipline.Zipline

private val zipline by lazy { Zipline.get(SerializersModule) }

@OptIn(ExperimentalJsExport::class)
@JsExport
fun preparePresenters() {
  val hostApi = zipline.take<HostApi>(
    name = "HostApi",
  )

  zipline.bind<SearchPresenter>(
    name = "SearchPresenter",
    instance = RealSearchPresenter(hostApi, zipline.json),
  )
}