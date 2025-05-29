package com.jasson.conforta.screen.mainscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jasson.conforta.dummy.DummyData

@Composable
fun CuidadoPaliativoCard(
    item: DummyData.GridItem,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        border = CardDefaults.outlinedCardBorder(),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
        ) {
            Icon(
                imageVector = item.iconRes,
                contentDescription = item.title,
                modifier = Modifier
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                lineHeight = MaterialTheme.typography.titleMedium.lineHeight,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun CuidadoPaliativoSubCard(
    item: DummyData.SubGridItem,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        border = CardDefaults.outlinedCardBorder(),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
        ) {
            Icon(
                imageVector = item.iconRes,
                contentDescription = item.title,
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                lineHeight = MaterialTheme.typography.titleMedium.lineHeight,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }
    }
}