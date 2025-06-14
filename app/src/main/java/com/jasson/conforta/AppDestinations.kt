package com.jasson.conforta

import androidx.annotation.StringRes
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.jasson.conforta.domain.model.CuidadoPaliativoAgrupado
import com.jasson.conforta.domain.model.DetalheSubcategoria
import kotlinx.serialization.json.Json
import java.net.URLEncoder

const val SUB_CATEGORY_JSON_ARG = "sub_category_json"
const val DETAIL_JSON_ARG = "detail_json"

sealed class AppDestinations(
    val route: String,
    @StringRes val stringId: Int,
) {
    data object MainDestinations : AppDestinations(
        route = "main",
        stringId = R.string.app_name
    )

    data object SubDestinations : AppDestinations(
        route = "subcategories/{$SUB_CATEGORY_JSON_ARG}",
        stringId = R.string.categorias,
    ) {
        val arguments = listOf(
            navArgument(SUB_CATEGORY_JSON_ARG) { type = NavType.StringType }
        )
        fun buildRoute(subCategoriaJson: CuidadoPaliativoAgrupado) : String {
            val json = Json.encodeToString(
                CuidadoPaliativoAgrupado.serializer(),
                subCategoriaJson
            )
            val encodedJson = URLEncoder.encode(json, "utf-8")
            return "subcategories/$encodedJson"
        }
    }

    data object DetailDestinations : AppDestinations(
        "detail/{$DETAIL_JSON_ARG}",
        stringId = R.string.procedimento
    ) {
        val arguments = listOf(
            navArgument(DETAIL_JSON_ARG) { type = NavType.StringType }
        )
        fun buildRoute(detailJson: DetalheSubcategoria): String {
            val json = Json.encodeToString(
                DetalheSubcategoria.serializer(),
                detailJson
            )
            val encodedJson = URLEncoder.encode(json, "utf-8")
            return "detail/$encodedJson"
        }

    }

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