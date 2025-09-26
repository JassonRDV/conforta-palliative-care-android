package com.jasson.conforta.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaliativeCareResponse(
    @SerialName("Cuidado Paliativo")
    val itens: List<PaliativeCareItemDto>
)

@Serializable
data class PaliativeCareItemDto(
    @SerialName("Cuidado Paliativo")
    val categoria: String,

    @SerialName("Subcategoria")
    val subcategoria: String,

    @SerialName("Descrição")
    val descricao: String,
)

@Serializable
data class DetalheSubcategoria(
    val nomeSubcategoria: String,
    val descricao: String,
)

@Serializable
data class CuidadoPaliativoAgrupado(
    val nomeCategoriaPrincipal: String,
    val subcategorias: List<DetalheSubcategoria>
)