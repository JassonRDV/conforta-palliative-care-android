package com.jasson.conforta.dummy

import com.jasson.conforta.R

object DummyData {

    data class GridItem(val id: Int, val title: String, val iconRes: Int)
    data class SubGridItem(val id: Int, val title: String, val iconRes: Int)
    data class SubDetalhe(
        val titulo: String,
        val texto: String,
        val referencia: String,
        val referenciaList: String
    )

    val sampleGridItems = listOf(
        GridItem(1, "Item 1", R.drawable.pe_machucado),
        GridItem(2, "Item 2", R.drawable.pe_machucado),
        GridItem(3, "Item 3", R.drawable.pe_machucado),
        GridItem(4, "Item 4", R.drawable.pe_machucado),
        GridItem(5, "Item 5", R.drawable.pe_machucado),
        GridItem(6, "Item 6", R.drawable.pe_machucado),
        GridItem(7, "Item 7", R.drawable.pe_machucado),
        GridItem(8, "Item 8", R.drawable.pe_machucado),
        GridItem(9, "Item 9", R.drawable.pe_machucado),
        GridItem(10, "Item 10", R.drawable.pe_machucado)
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