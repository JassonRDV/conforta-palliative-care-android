package com.jasson.conforta.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jasson.conforta.domain.model.PaliativeCareItemDto

@Entity(tableName = "conforta_table")
data class PaliativeCareItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String,
    val subcategoria: String,
    val descricao: String,
)

fun PaliativeCareItemDto.toEntity(): PaliativeCareItemEntity {
    return PaliativeCareItemEntity(
        subcategoria = this.subcategoria,
        descricao = this.descricao,
        category = this.categoria,
    )
}

fun PaliativeCareItemEntity.toDto(): PaliativeCareItemDto {
    return PaliativeCareItemDto(
        categoria = this.category,
        subcategoria = this.subcategoria,
        descricao = this.descricao,
    )
}