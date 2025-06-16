package com.jasson.conforta

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jasson.conforta.domain.model.CuidadoPaliativoAgrupado
import com.jasson.conforta.domain.model.DetalheSubcategoria
import com.jasson.conforta.ui.screen.bar.AppBottomBar
import com.jasson.conforta.ui.screen.detailscreen.CuidadoPaliativoDetalhe
import com.jasson.conforta.ui.screen.mainscreen.MainScreen
import com.jasson.conforta.ui.screen.subscreen.SubGridCuidadoPaliativo
import com.jasson.conforta.ui.screen.bar.AppTopBar
import kotlinx.serialization.json.Json
import java.net.URLDecoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator(
    navController: NavHostController = rememberNavController(),
    mainViewModel: MainViewModel = hiltViewModel(),
) {
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
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
        bottomBar = {
            AppBottomBar(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(12.dp)
                    .fillMaxWidth()
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestinations.MainDestinations.route,
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 4.dp)
        ) {
            composable(route = AppDestinations.MainDestinations.route) {
                MainScreen(
                    modifier = Modifier,
                    navController = navController,
                    uiState = uiState.cuidadosAgrupados,
                )
            }
            composable(
                route = AppDestinations.SubDestinations.route,
                arguments = AppDestinations.SubDestinations.arguments
            ) { backStackEntry ->
                val jsonSubCategory = backStackEntry.arguments?.getString(SUB_CATEGORY_JSON_ARG)
                val detalheObject = jsonSubCategory.let {
                    val decodedJson = URLDecoder.decode(it, "UTF-8")
                    Json.decodeFromString<CuidadoPaliativoAgrupado>(decodedJson.toString())
                }
                SubGridCuidadoPaliativo(
                    navController = navController,
                    subGridItems = detalheObject
                )
            }
            composable(
                route = AppDestinations.DetailDestinations.route,
            ) { backStackEntry ->
                val jsonSubDetail = backStackEntry.arguments?.getString(DETAIL_JSON_ARG)
                val detailObject = jsonSubDetail.let {
                    val decodedJson = URLDecoder.decode(it, "UTF-8")
                    Json.decodeFromString<DetalheSubcategoria>(decodedJson.toString())
                }
                CuidadoPaliativoDetalhe(
                    uiState = detailObject,
                )
            }
        }
    }
}


