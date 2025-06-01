package com.jasson.conforta.data.local

import com.jasson.conforta.data.model.dummy.DummyData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepositoryImpl @Inject constructor(
    private val localDataSource: DummyData
) : DataRepository {

    override fun getGridItems(): List<DummyData.GridItem> =
        localDataSource.sampleGridItems
}