package com.jasson.obesidade.ui.screen.subscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jasson.obesidade.AppDestinations
import com.jasson.obesidade.domain.model.CuidadoPaliativoAgrupado

@Composable
fun SubGridCuidadoPaliativo(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    subGridItems: CuidadoPaliativoAgrupado,
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 4.dp),
    ) {
        items(subGridItems.subcategorias.size) { item ->
            val gridItem = subGridItems.subcategorias[item]
            CuidadoPaliativoSubCard(
                item = gridItem,
                modifier = Modifier
                    .clickable {
                        val route = AppDestinations.DetailDestinations.buildRoute(gridItem)
                        navController.navigate(route)
                    }
                    .padding(4.dp)
            )
        }
    }
}