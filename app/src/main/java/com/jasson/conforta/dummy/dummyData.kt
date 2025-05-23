package com.jasson.conforta.dummy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.AirlineSeatLegroomExtra
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.Scale
import androidx.compose.ui.graphics.vector.ImageVector
import com.jasson.conforta.R

object DummyData {

    data class GridItem(val id: Int, val title: String, val iconRes: ImageVector)
    data class SubGridItem(val id: Int, val title: String, val iconRes: Int)
    data class SubDetalhe(
        val titulo: String,
        val texto: String,
        val referencia: String,
        val referenciaList: String
    )

    val sampleGridItems = listOf(
        GridItem(1, "Item 1", Icons.Filled.Healing),
        GridItem(2, "Item 2", Icons.Filled.Healing),
        GridItem(3, "Item 3", Icons.Filled.Hearing),
        GridItem(4, "Item 4", Icons.Filled.AirlineSeatLegroomExtra),
        GridItem(5, "Item 5", Icons.Filled.Scale),
        GridItem(6, "Item 6", Icons.Filled.Healing),
        GridItem(7, "Item 7", Icons.Filled.AddBox),
        GridItem(8, "Item 8", Icons.Filled.Healing),
        GridItem(9, "Item 9", Icons.Filled.AddBox),
        GridItem(10, "Item 10", Icons.Filled.Healing)
    )

    val sampleSubDetail = SubDetalhe(
        titulo = "Entendendo os Cuidados Paliativos",
        texto = """
            Os cuidados paliativos são uma abordagem de assistência promovida por uma equipe multiprofissional,
            que objetiva a melhoria da qualidade de vida do paciente e seus familiares, diante de uma doença
            que ameace a vida, por meio da prevenção e alívio do sofrimento, da identificação precoce, 
            avaliação impecável e tratamento de dor e demais sintomas físicos, sociais, psicológicos e espirituais.

            Eles não se limitam apenas aos momentos finais da vida, podendo ser iniciados desde o diagnóstico
            de doenças crônicas progressivas, juntamente com o tratamento curativo. O foco é proporcionar
            conforto, dignidade e suporte integral.
        """.trimIndent(),
        referencia = "Organização Mundial da Saúde (OMS), 2020.",
        referenciaList = """
            - Academia Nacional de Cuidados Paliativos (ANCP)
            - Instituto Paliar
            - Ministério da Saúde - Caderno de Atenção Domiciliar (Volume 2)
        """.trimIndent()
    )
}