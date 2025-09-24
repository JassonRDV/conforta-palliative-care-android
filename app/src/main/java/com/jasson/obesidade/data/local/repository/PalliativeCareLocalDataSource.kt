package com.jasson.conforta.data.local.repository

import com.jasson.conforta.data.local.PaliativeCareItemEntity

interface PalliativeCareLocalDataSource {
    suspend fun getPalliativeCareItems(): List<PaliativeCareItemEntity>
    suspend fun savePalliativeCareItems(items: List<PaliativeCareItemEntity>)
    suspend fun clearAllPalliativeCareItems()
    suspend fun hasCachedData(): Boolean
}