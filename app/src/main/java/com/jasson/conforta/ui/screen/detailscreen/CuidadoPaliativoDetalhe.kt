package com.jasson.conforta.ui.screen.detailscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jasson.conforta.R
import com.jasson.conforta.data.remote.DetalheSubcategoria

@Composable
fun CuidadoPaliativoDetalhe(
    uiState: DetalheSubcategoria?,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = uiState?.descricao ?: "text",
                textAlign = MaterialTheme.typography.bodyLarge.textAlign,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 12.dp,
                        top = 2.dp
                    )
            )
        }
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = stringResource(R.string.referencia),
                textAlign = TextAlign.Center,
                fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 2.dp,
                        top = 12.dp
                    )
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = uiState?.referencia ?: "referenceList",
                fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 12.dp,
                        top = 2.dp
                    )
                    .align(Alignment.Start)
            )
        }
    }
}