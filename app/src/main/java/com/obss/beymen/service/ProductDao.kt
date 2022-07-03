package com.obss.beymen.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.obss.beymen.model.FavoriteModel

@Dao
interface ProductDao {

    @Insert
    suspend fun insertAll(vararg favoriteModel: FavoriteModel): List<Long>

    @Query("SELECT * FROM FavoriteModel")
    suspend fun getAllFavorite(): List<FavoriteModel>

    @Query("DELETE FROM FavoriteModel WHERE productId = :id")
    suspend fun deleteById(id: Int)


}