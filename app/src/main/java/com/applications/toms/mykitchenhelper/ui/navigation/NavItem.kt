package com.applications.toms.mykitchenhelper.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.applications.toms.mykitchenhelper.R

enum class NavItem(
    val navCommand: NavCommand,
    val icon: ImageVector,
    @StringRes val title: Int
) {
    HOME(NavCommand.ContentType(NavFeature.HOME), Icons.Default.Home, R.string.home),
    ABOUT_US(NavCommand.ContentType(NavFeature.ABOUT_US), Icons.Default.Info, R.string.about_us)
}

sealed class NavCommand(
    internal val feature: NavFeature,
    internal val subRoute: String = "home",
    val navArgs: List<NavArg> = emptyList()
) {
    class ContentType(feature: NavFeature): NavCommand(feature = feature)

    /*
        Detail Screen in case we need it
        class ContentDetail(feature: NavFeature): NavCommand(feature = feature, subRoute = "detail") {
            fun createNavRout() = "${feature.route}/$subRoute"
        }
        class ContentDetail(feature: Feature): NavCommand(feature = feature, "detail", listOf(NavArg.Id)) {
            fun createNavRout(id: Int) = "${feature.route}/$subRoute/$id"
        }
     */

    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(feature.route, subRoute).plus(argKeys).joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }
}

enum class NavArg(
    val key: String,
    val navType: NavType<*>
) {
    // Add arguments here
}