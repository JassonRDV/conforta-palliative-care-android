package com.jasson.conforta.local

import com.jasson.conforta.dummy.DummyData

interface DataRepository {
    fun getGridItems(): List<DummyData.GridItem>
}