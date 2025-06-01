package com.jasson.conforta.data.local

import com.jasson.conforta.data.model.dummy.DummyData

interface DataRepository {
    fun getGridItems(): List<DummyData.GridItem>
}