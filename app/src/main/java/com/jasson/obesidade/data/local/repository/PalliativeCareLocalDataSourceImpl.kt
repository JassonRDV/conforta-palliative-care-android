package com.jasson.obesidade.data.local.repository

import com.jasson.obesidade.data.local.PaliativeCareItemEntity
import com.jasson.obesidade.data.local.PalliativeCareDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PalliativeCareLocalDataSourceImpl @Inject constructor(
    private val palliativeCareDao: PalliativeCareDao
) : PalliativeCareLocalDataSource {

    override suspend fun getPalliativeCareItems(): List<PaliativeCareItemEntity> {
        return palliativeCareDao.getAll()
    }

    override suspend fun savePalliativeCareItems(items: List<PaliativeCareItemEntity>) {
        palliativeCareDao.clearAll()
        palliativeCareDao.insertAll(items)
    }

    override suspend fun clearAllPalliativeCareItems() {
        palliativeCareDao.clearAll()
    }

    override suspend fun hasCachedData(): Boolean {
        return palliativeCareDao.getAll().isNotEmpty()
    }
}