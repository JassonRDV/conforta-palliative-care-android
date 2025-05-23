package com.jasson.conforta.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
fun SubGridCuidadoPaliativo(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 4.dp),
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConfortaTheme {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = stringResource(R.string.app_name),
                    onNavigationIconClick = { /* TODO: Lógica para navegação */ }
                )
            },
        ) { innerPadding ->
            SubGridCuidadoPaliativo(modifier = Modifier.padding(innerPadding))
        }
    }
}