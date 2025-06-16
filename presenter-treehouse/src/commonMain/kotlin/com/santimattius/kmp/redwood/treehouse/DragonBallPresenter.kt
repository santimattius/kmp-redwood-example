package com.santimattius.kmp.redwood.treehouse

import app.cash.redwood.treehouse.AppService
import app.cash.redwood.treehouse.ZiplineTreehouseUi
import app.cash.zipline.ZiplineService
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("EmojiSearchPresenter", exact = true)
interface SearchPresenter : AppService, ZiplineService {
    fun launch(): ZiplineTreehouseUi
}