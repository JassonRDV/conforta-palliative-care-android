package com.jasson.conforta.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaliativeCareResponse(
    @SerialName("Cuidado Paliativo")
    val data: List<PaliativeCareItemDto>
)

@Serializable
data class PaliativeCareItemDto(
    @SerialName("Cuidado Paliativo")
    val cuidadoPaliativo: String,

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