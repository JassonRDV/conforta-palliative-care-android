package com.jasson.conforta.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jasson.conforta.data.remote.PaliativeCareItemDto

@Entity(tableName = "conforta_table")
data class PaliativeCareItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String,
    val subcategoria: String,
    val descricao: String,
    val profissionalEnvolvido: List<String>,
    val nivelDeUrgencia: String,
    val reference: String
)

fun PaliativeCareItemDto.toEntity(): PaliativeCareItemEntity {
    return PaliativeCareItemEntity(
        subcategoria = this.subcategoria,
        descricao = this.descricao,
        profissionalEnvolvido = this.profissionalEnvolvido,
        nivelDeUrgencia = this.nivelDeUrgencia,
        category = this.categoria,
        reference = this.referencia
    )
}

fun PaliativeCareItemEntity.toDto(): PaliativeCareItemDto {
    return PaliativeCareItemDto(
        categoria = this.category,
        subcategoria = this.subcategoria,
        descricao = this.descricao,
        profissionalEnvolvido = this.profissionalEnvolvido,
        nivelDeUrgencia = this.nivelDeUrgencia,
        referencia = this.reference
    )
}