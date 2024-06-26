package com.stewemetal.takehometemplate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.stewemetal.takehometemplate.itemdetails.contract.ItemDetailsNavGraphFactory
import com.stewemetal.takehometemplate.itemdetails.contract.navigateToItemDetails
import com.stewemetal.takehometemplate.itemlist.contract.ItemListNavGraphFactory
import com.stewemetal.takehometemplate.login.contract.LoginNavGraphFactory
import com.stewemetal.takehometemplate.login.contract.MapNavGraphFactory
import com.stewemetal.takehometemplate.login.contract.MapRoute
import com.stewemetal.takehometemplate.shell.navigation.compose.TakeHomeTemplateNavHost
import org.koin.compose.koinInject

@Composable
fun TakeHomeTemplateApp(
    modifier: Modifier = Modifier,
    loginNavGraphFactory: LoginNavGraphFactory = koinInject(),
    itemListNavGraphFactory: ItemListNavGraphFactory = koinInject(),
    itemDetailsNavGraphFactory: ItemDetailsNavGraphFactory = koinInject(),
    mapNavGraphFactory: MapNavGraphFactory = koinInject(),
) {
    fun NavBackStackEntry.lifecycleIsResumed(): Boolean =
        this.lifecycle.currentState == Lifecycle.State.RESUMED

    fun NavController.canNavigate(): Boolean =
        currentBackStackEntry?.lifecycleIsResumed() ?: false

    fun NavController.debouncedNavigation(block: NavController.() -> Unit) {
        if (canNavigate()) {
            block()
        }
    }

    val navController = rememberNavController()
    TakeHomeTemplateNavHost(
        navController = navController,
        startDestination = MapRoute,
        modifier = modifier,
    ) {
        mapNavGraphFactory.buildNavGraph(
            navGraphBuilder = this,
        )

        itemListNavGraphFactory.buildNavGraph(
            navGraphBuilder = this,
            onNavigateBack = {
                navController.debouncedNavigation {
                    navigateUp()
                }
            },
            onNavigateToDetailsScreen = { itemId ->
                navController.debouncedNavigation {
                    navigateToItemDetails(itemId)
                }
            },
        )

        itemDetailsNavGraphFactory.buildNavGraph(
            navGraphBuilder = this,
            onNavigateBack = {
                navController.debouncedNavigation {
                    navigateUp()
                }
            },
        )
    }
}
