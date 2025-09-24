package com.jasson.obesidade

import android.util.Log
import androidx.annotation.StringRes
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.jasson.obesidade.domain.model.CuidadoPaliativoAgrupado
import com.jasson.obesidade.domain.model.DetalheSubcategoria
import kotlinx.serialization.json.Json
import java.net.URLEncoder

const val SUB_CATEGORY_JSON_ARG = "sub_category_json"
const val DETAIL_JSON_ARG = "detail_json"

/**
 * Sealed class representing the different destinations in the application.
 *
 * Each destination has a unique [route] string used for navigation and a [stringId]
 * which is a string resource ID for the title of the screen.
 *
 * This class also provides helper functions for building routes with arguments and
 * retrieving a destination from a given route string.
 *
 * @property route The unique string identifier for the destination.
 * @property stringId The string resource ID for the title of the destination.
 */
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
            var encodedJson = URLEncoder.encode(json, "utf-8")
            encodedJson = encodedJson.replace("+", "%20")
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
            var encodedJson = URLEncoder.encode(json, "utf-8")
            encodedJson = encodedJson.replace("+", "%20")
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
                route?.startsWith("subcategories") == true -> SubDestinations
                else -> MainDestinations
            }
        }
    }
}