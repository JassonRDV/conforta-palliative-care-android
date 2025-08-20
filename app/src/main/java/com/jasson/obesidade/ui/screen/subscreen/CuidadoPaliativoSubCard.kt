package com.jasson.obesidade.ui.screen.subscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jasson.obesidade.domain.model.DetalheSubcategoria

@Composable
fun CuidadoPaliativoSubCard(
    item: DetalheSubcategoria,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFe6ccef),
        ),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.Companion.CenterVertically,
            modifier = Modifier.Companion
                .padding(12.dp)
        ) {
            Text(
                text = item.nomeSubcategoria,
                style = MaterialTheme.typography.titleMedium,
                lineHeight = MaterialTheme.typography.titleMedium.lineHeight,
                maxLines = 1,
                textAlign = TextAlign.Companion.Center,
                modifier = Modifier.Companion.weight(1f)
            )
        }
    }
}