package com.jasson.conforta.ui.screen.mainscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jasson.conforta.AppDestinations
import com.jasson.conforta.domain.model.CuidadoPaliativoAgrupado

@Composable
fun MainScreen(
    uiState: List<CuidadoPaliativoAgrupado>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
        ) {
            items(uiState.size) { index ->
                val item = uiState[index]
                CuidadoPaliativoCard(
                    item = item,
                    modifier = Modifier
                        .clickable {
                            val route = AppDestinations.SubDestinations.buildRoute(item)
                            navController.navigate(route)
                        }
                        .padding(4.dp)
                )
            }
        }
    }
}