package com.jasson.conforta.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jasson.conforta.R
import com.jasson.conforta.screen.topbar.AppTopBar
import com.jasson.conforta.ui.theme.ConfortaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppConforta() {
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
            LazyGrid(
                modifier = Modifier.Companion
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
            )
        }
    }
}