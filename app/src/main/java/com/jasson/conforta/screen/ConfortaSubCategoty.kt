package com.jasson.conforta.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jasson.conforta.R
import com.jasson.conforta.dummy.DummyData
import com.jasson.conforta.screen.topbar.AppTopBar
import com.jasson.conforta.ui.theme.ConfortaTheme

@Composable
fun ConfortaSubCategoty(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(DummyData.sampleGridItems.size) { item ->
            val Item = DummyData.sampleGridItems[item]
            Card(
                onClick = { /* TODO: Lógica para navegação */ },
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier
                    .aspectRatio(4f)
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(Item.iconRes),
                        contentDescription = Item.title,
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(CircleShape)
                            .weight(0.5f, false)
                    )
                    Text(
                        text = Item.title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f, true)
                            .padding(start = 8.dp, end = 8.dp)
                    )
                }
            }
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
            modifier = Modifier.Companion.fillMaxSize()
        ) { innerPadding ->
            ConfortaSubCategoty(modifier = Modifier.padding(innerPadding))
        }
    }
}