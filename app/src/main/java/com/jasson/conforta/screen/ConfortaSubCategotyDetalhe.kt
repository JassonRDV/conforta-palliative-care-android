package com.jasson.conforta.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jasson.conforta.R
import com.jasson.conforta.dummy.DummyData
import com.jasson.conforta.screen.topbar.AppTopBar
import com.jasson.conforta.ui.theme.ConfortaTheme

@Composable
fun ConfortaSubCategotyDetalhe(
    item: DummyData.SubDetalhe,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .scrollable(
                ScrollState(0),
                Orientation.Vertical,
                true,
                true
                )
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = item.titulo,
                textAlign = TextAlign.Center,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,

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
                text = item.texto,
                textAlign = TextAlign.Unspecified,
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
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
        Card(
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = "Referência",
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
                text = item.referenciaList,
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
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
            ConfortaSubCategotyDetalhe(
                item = DummyData.sampleSubDetail,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}