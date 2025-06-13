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

    @SerialName("Profissional Envolvido")
    val profissionalEnvolvido: List<String>,

    @SerialName("Nível de Urgência")
    val nivelDeUrgencia: String,

    @SerialName("Referência")
    val referencia: String
)

@Serializable
data class DetalheSubcategoria(
    val nomeSubcategoria: String,
    val descricao: String,
    val profissionalEnvolvido: List<String>,
    val nivelDeUrgencia: String,
    val referencia: String?
)

data class CuidadoPaliativoAgrupado(
    val nomeCategoriaPrincipal: String,
    val subcategorias: List<DetalheSubcategoria>
)