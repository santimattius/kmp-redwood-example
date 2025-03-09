package com.santimattius.kmp.redwood.treehouse

import app.cash.redwood.treehouse.StandardAppLifecycle
import app.cash.redwood.treehouse.ZiplineTreehouseUi
import app.cash.redwood.treehouse.asZiplineTreehouseUi
import com.santimattius.kmp.redwood.example.protocol.guest.DragonBallProtocolWidgetSystemFactory
import com.santimattius.kmp.redwood.presenter.remote.DragonBallSearchTreehouseUi
import com.santimattius.kmp.redwood.presenter.remote.Navigator
import kotlinx.serialization.json.Json

class RealSearchPresenter(
    private val hostApi: HostApi,
    json: Json,
) : SearchPresenter {

    override val appLifecycle = StandardAppLifecycle(
        protocolWidgetSystemFactory = DragonBallProtocolWidgetSystemFactory,
        json = json,
        widgetVersion = 0U,
    )

    override fun launch(): ZiplineTreehouseUi {

        val navigator = object : Navigator {
            override fun openUrl(url: String) {
                hostApi.openUrl(url)
            }
        }

        val treehouseUi = DragonBallSearchTreehouseUi(hostApi::httpCall, navigator)

        return treehouseUi.asZiplineTreehouseUi(
            appLifecycle = appLifecycle,
        )
    }
}