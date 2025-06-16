package com.santimattius.kmp.redwood.launcher

import app.cash.redwood.treehouse.TreehouseApp
import app.cash.zipline.Zipline
import app.cash.zipline.ZiplineManifest
import app.cash.zipline.loader.FreshnessChecker
import com.santimattius.kmp.redwood.treehouse.HostApi
import com.santimattius.kmp.redwood.treehouse.SearchPresenter
import com.santimattius.kmp.redwood.treehouse.SerializersModule
import kotlinx.coroutines.flow.Flow

class AppSpec(
    override val manifestUrl: Flow<String>,
    private val hostApi: HostApi,
) : TreehouseApp.Spec<SearchPresenter>() {

    override val name get() = "search"

    override val serializersModule get() = SerializersModule

    override val freshnessChecker = object : FreshnessChecker {
        override fun isFresh(manifest: ZiplineManifest, freshAtEpochMs: Long) = true
    }

    override suspend fun bindServices(
        treehouseApp: TreehouseApp<SearchPresenter>,
        zipline: Zipline,
    ) {
        zipline.bind<HostApi>("HostApi", hostApi)
    }

    override fun create(zipline: Zipline): SearchPresenter {
        return zipline.take<SearchPresenter>("SearchPresenter")
    }
}