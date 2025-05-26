package com.jasson.conforta

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jasson.conforta.screen.detailscreen.CuidadoPaliativoDetalhe
import com.jasson.conforta.screen.mainscreen.MainScreen
import com.jasson.conforta.screen.subscreen.SubGridCuidadoPaliativo
import com.jasson.conforta.screen.topbar.AppTopBar

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfortaApp(
    navController: NavHostController = rememberNavController(),
    viewModel: ViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentScreen = remember(currentRoute) {
        AppScreen.fromRoute(currentRoute)
    }
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(id = currentScreen.stringId),
                onNavigationIconClick = {
                    navController.navigateUp()
                }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.MainScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = AppScreen.MainScreen.route
            ) {
                MainScreen(
                    modifier = Modifier,
                    navController = navController,
                    uiState = uiState.list,
                    viewModel = viewModel
                )
            }
            composable(
                route = AppScreen.SubScreen.route,
            ) { backStackEntry ->
                SubGridCuidadoPaliativo(
                    uiState = uiState.subList,
                    viewModel = viewModel,
                    navController = navController
                )
            }
            composable(
                route = AppScreen.DetailScreen.route,
            ) {
                CuidadoPaliativoDetalhe(
                    uiState = uiState.detail,
                )
            }
        }
    }
}




