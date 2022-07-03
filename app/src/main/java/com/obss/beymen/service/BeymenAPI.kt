package com.obss.beymen.service

import com.obss.beymen.model.ListModel
import com.obss.beymen.model.ProductModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BeymenAPI {
    @GET("list")
    fun getProductList(
        @Query("siralama") sorted: String,
        @Query("sayfa") page: Int,
        @Query("categoryId") categoryId: Int,
        @Query("includeDocuments") includeDocuments: Boolean
    ): Single<ListModel>

    @GET("product")
    fun getProduct(@Query("productid") productId: Int): Single<ProductModel>

}