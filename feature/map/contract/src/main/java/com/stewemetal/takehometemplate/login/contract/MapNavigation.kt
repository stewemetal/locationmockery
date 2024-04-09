package com.stewemetal.takehometemplate.login.contract

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder

const val MapRoute = "map"

interface MapNavGraphFactory {
    fun buildNavGraph(
        navGraphBuilder: NavGraphBuilder,
    )
}

fun NavController.navigateToMap(
    navOptionsBuilder: NavOptionsBuilder.() -> Unit = { },
) {
    this.navigate(MapRoute, navOptionsBuilder)
}
