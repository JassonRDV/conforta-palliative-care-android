package com.jasson.conforta

import androidx.annotation.StringRes

sealed class AppScreen(
    val route: String,
    @StringRes val stringId: Int,
) {
    data object MainScreen : AppScreen(
        "main",
        R.string.app_name
    )

    data object SubScreen : AppScreen(
        route = "sub_screen",
        stringId = R.string.categorias,
    )

    data object DetailScreen : AppScreen(
        "detail/{subItemTitle}",
        stringId = R.string.procedimento
    )

    companion object {
        val screens = listOf(MainScreen, SubScreen, DetailScreen)

        fun fromRoute(route: String?): AppScreen {
            return screens.find { it.route == route } ?: MainScreen
        }
    }
}