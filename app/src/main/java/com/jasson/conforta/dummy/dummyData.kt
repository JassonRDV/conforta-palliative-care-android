package com.jasson.conforta.dummy

import com.jasson.conforta.R

object DummyData {
    data class GridItem(val id: Int, val title: String, val iconRes:Int)
    val sampleGridItems = listOf(
        GridItem(1, "Item 1", R.drawable.pe_machucado),
        GridItem(2, "Item 2", R.drawable.pe_machucado),
        GridItem(3, "Item 3", R.drawable.pe_machucado),
        GridItem(4, "Item 4", R.drawable.pe_machucado),
        GridItem(5, "Item 5", R.drawable.pe_machucado),
        GridItem(6, "Item 6", R.drawable.pe_machucado),
        GridItem(7, "Item 7", R.drawable.pe_machucado),
        GridItem(8, "Item 8", R.drawable.pe_machucado),
        GridItem(9, "Item 9", R.drawable.pe_machucado),
        GridItem(10, "Item 10", R.drawable.pe_machucado)
    )
}