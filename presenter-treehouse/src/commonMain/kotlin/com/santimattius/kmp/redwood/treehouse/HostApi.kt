package com.santimattius.kmp.redwood.treehouse

import app.cash.zipline.ZiplineService
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("HostApi", exact = true)
interface HostApi : ZiplineService {
  /** Decodes the response as a string and returns it. */
  suspend fun httpCall(url: String, headers: Map<String, String>): String

  fun openUrl(url: String)
}
