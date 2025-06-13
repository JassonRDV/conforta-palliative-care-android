package com.jasson.conforta.ui.screen.subscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jasson.conforta.AppDestinations
import com.jasson.conforta.ui.screen.mainscreen.ViewModel
import com.jasson.conforta.domain.model.DetalheSubcategoria

@Composable
fun SubGridCuidadoPaliativo(
    modifier: Modifier = Modifier,
    uiState: List<DetalheSubcategoria>,
    viewModel: ViewModel,
    navController: NavHostController,
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 4.dp),
    ) {
        items(uiState.size) { item ->
            val gridItem = uiState[item]
            CuidadoPaliativoSubCard(
                item = gridItem,
                modifier = Modifier
                    .clickable {
                        navController.navigate(
                            AppDestinations.DetailDestinations.route
                        )
                        viewModel.loadDetail(item)
                    }
                    .padding(4.dp)
            )
        }
    }
}