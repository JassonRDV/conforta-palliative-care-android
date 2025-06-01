package com.jasson.conforta.ui.screen.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jasson.conforta.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppTopBar(
    title: String,
    onNavigationIconClick: () -> Unit,
    canNavigateBack: Boolean
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall,
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigationIconClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.voltar)
                    )
                }
            }
        },
    )
}