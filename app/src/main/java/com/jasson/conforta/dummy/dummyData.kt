package com.jasson.conforta.dummy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CleanHands
import androidx.compose.material.icons.filled.EscalatorWarning
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.ui.graphics.vector.ImageVector

object DummyData {

    data class Detail(
        val title: String,
        val text: String,
        val referenceList: String
    )

    val sampleSubDetail = Detail(
        title = "Entendendo os Cuidados Paliativos",
        text = """
            Os cuidados paliativos são uma abordagem de assistência promovida por uma equipe multiprofissional, que objetiva a melhoria da qualidade de vida do paciente e seus familiares, diante de uma doença que ameace a vida, por meio da prevenção e alívio do sofrimento, da identificação precoce, avaliação impecável e tratamento de dor e demais sintomas físicos, sociais, psicológicos e espirituais.

            Eles não se limitam apenas aos momentos finais da vida, podendo ser iniciados desde o diagnóstico de doenças crônicas progressivas, juntamente com o tratamento curativo. O foco é proporcionar conforto, dignidade e suporte integral.
        """.trimIndent(),
        referenceList = """
            - Academia Nacional de Cuidados Paliativos (ANCP)
            - Instituto Paliar
            - Ministério da Saúde - Caderno de Atenção Domiciliar (Volume 2)
        """.trimIndent()
    )

    data class SubGridItem(
        val title: String,
        val iconRes: ImageVector,
        val subDetail: Detail = sampleSubDetail
    )

    val sampleSubGridItems = listOf(
        SubGridItem("Cuidados Paliativos", Icons.Filled.Healing),
        SubGridItem("Cuidados Paliativos", Icons.Filled.CleanHands),
        SubGridItem("Cuidados Paliativos", Icons.Filled.Healing),
        SubGridItem("Cuidados Paliativos", Icons.Filled.Healing),
        SubGridItem("Cuidados Paliativos", Icons.Filled.Healing),
        SubGridItem("Cuidados Paliativos", Icons.Filled.Healing),
    )

    data class GridItem(
        val title: String,
        val iconRes: ImageVector,
        val subGridItems: List<SubGridItem> = sampleSubGridItems
    )

    val sampleGridItems = listOf(
        GridItem("Controle da Dor", Icons.Filled.Healing),
        GridItem("Manejo de Sintomas", Icons.Filled.Medication),
        GridItem("Suporte Emocional", Icons.Filled.Favorite),
        GridItem("Apoio Psicossocial", Icons.Filled.Psychology),
        GridItem("Comunicação Clara", Icons.AutoMirrored.Filled.Chat),
        GridItem("Cuidados Domiciliares", Icons.Filled.Home),
        GridItem("Apoio à Família", Icons.Filled.EscalatorWarning),
        GridItem("Suporte Espiritual", Icons.Filled.SelfImprovement),
        GridItem("Qualidade de Vida", Icons.Filled.AccessibilityNew),
        GridItem("Multidisciplinar", Icons.Filled.SupportAgent),
        GridItem("Hegiene", Icons.Filled.CleanHands),
    )
}