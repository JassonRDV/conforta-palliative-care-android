package com.jasson.conforta

import androidx.annotation.StringRes

sealed class AppDestinations(
    val route: String,
    @StringRes val stringId: Int,
) {
    data object MainDestinations : AppDestinations(
        route = "main",
        stringId = R.string.app_name
    )

    data object SubDestinations : AppDestinations(
        route = "sub_screen/{categoria_json}",
        stringId = R.string.categorias,
    )

    data object DetailDestinations : AppDestinations(
        "detail/{detalhe_json}",
        stringId = R.string.procedimento
    )

    companion object {
        val screens = listOf(
            MainDestinations,
            SubDestinations,
            DetailDestinations
        )

        fun fromRoute(route: String?): AppDestinations {
            return when {
                route?.startsWith("detail") == true -> DetailDestinations
                route?.startsWith("sub_screen") == true -> SubDestinations
                else -> MainDestinations
            }
        }
    }
}