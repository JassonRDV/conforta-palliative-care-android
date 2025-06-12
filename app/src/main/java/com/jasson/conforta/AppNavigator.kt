package com.jasson.conforta

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jasson.conforta.ui.screen.detailscreen.CuidadoPaliativoDetalhe
import com.jasson.conforta.ui.screen.mainscreen.MainScreen
import com.jasson.conforta.ui.screen.subscreen.SubGridCuidadoPaliativo
import com.jasson.conforta.ui.screen.topbar.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator(
    navController: NavHostController = rememberNavController(),
    viewModel: ViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentScreen = remember(currentRoute) {
        AppDestinations.fromRoute(currentRoute)
    }
    Scaffold(
        topBar = {
            AppTopBar(
                title = stringResource(id = currentScreen.stringId),
                canNavigateBack = currentScreen != AppDestinations.MainDestinations &&
                        navController.previousBackStackEntry != null,
                onNavigationIconClick = {
                    navController.navigateUp()
                }
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestinations.MainDestinations.route,
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 4.dp)
        ) {
            composable(
                route = AppDestinations.MainDestinations.route
            ) {
                MainScreen(
                    modifier = Modifier,
                    navController = navController,
                    uiState = uiState.cuidadosAgrupados,
                    viewModel = viewModel
                )
            }
            composable(
                route = AppDestinations.SubDestinations.route,
            ) {
                SubGridCuidadoPaliativo(
                    uiState = uiState.subGridItems,
                    viewModel = viewModel,
                    navController = navController
                )
            }
            composable(
                route = AppDestinations.DetailDestinations.route,
            ) {
                CuidadoPaliativoDetalhe(
                    uiState = uiState.detail,
                )
            }
        }
    }
}




