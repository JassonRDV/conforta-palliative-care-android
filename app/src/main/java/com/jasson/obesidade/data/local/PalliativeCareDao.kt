package com.jasson.obesidade.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PalliativeCareDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<PaliativeCareItemEntity>)

    @Query("SELECT * FROM conforta_table")
    suspend fun getAll(): List<PaliativeCareItemEntity>

    @Query("DELETE FROM conforta_table")
    suspend fun clearAll()
}