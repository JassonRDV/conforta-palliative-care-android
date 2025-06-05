package com.jasson.conforta

import androidx.annotation.StringRes

sealed class AppDestinations(
    val route: String,
    @StringRes val stringId: Int,
) {
    data object MainDestinations : AppDestinations(
        "main",
        R.string.app_name
    )

    data object SubDestinations : AppDestinations(
        route = "sub_screen",
        stringId = R.string.categorias,
    )

    data object DetailDestinations : AppDestinations(
        "detail/{subItemTitle}",
        stringId = R.string.procedimento
    )

    companion object {
        val screens = listOf(
            MainDestinations,
            SubDestinations,
            DetailDestinations
        )

        fun fromRoute(route: String?): AppDestinations {
            return screens.find { it.route == route } ?: MainDestinations
        }
    }
}