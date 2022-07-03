package com.obss.beymen.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteModel(
    @PrimaryKey(autoGenerate = false)
    var productId: Int = 0

)