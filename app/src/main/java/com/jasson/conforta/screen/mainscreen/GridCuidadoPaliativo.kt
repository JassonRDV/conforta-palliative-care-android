package com.jasson.conforta.screen.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jasson.conforta.R
import com.jasson.conforta.dummy.DummyData
import com.jasson.conforta.screen.common.CuidadoPaliativo
import com.jasson.conforta.screen.topbar.AppTopBar
import com.jasson.conforta.ui.theme.ConfortaTheme

@Composable
fun GridCuidadoPaliativo(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
        ) {
            items(DummyData.sampleGridItems.size) { item ->
                val gridItem = DummyData.sampleGridItems[item]
                CuidadoPaliativo(
                    item = gridItem,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    ConfortaTheme {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = stringResource(R.string.app_name),
                    onNavigationIconClick = { /* TODO: Lógica para navegação */ }
                )
            },
            modifier = Modifier.Companion.fillMaxSize()
        ) { innerPadding ->
            GridCuidadoPaliativo(modifier = Modifier.padding(innerPadding))
        }
    }
}