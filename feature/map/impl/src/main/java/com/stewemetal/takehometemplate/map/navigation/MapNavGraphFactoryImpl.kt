package com.stewemetal.takehometemplate.map.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stewemetal.takehometemplate.login.contract.MapNavGraphFactory
import com.stewemetal.takehometemplate.login.contract.MapRoute
import com.stewemetal.takehometemplate.map.ui.MapScreen

internal class MapNavGraphFactoryImpl : MapNavGraphFactory {
    override fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
    ) {
        navGraphBuilder.composable(MapRoute) {
            MapScreen()
        }
    }
}
