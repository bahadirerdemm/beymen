package com.obss.beymen.model

data class ListModel(
    val Exception: Any,
    val IsOriginFromCheckout: Boolean,
    val Message: Any,
    val Result: Result,
    val Success: Boolean
)

data class Result(
    val Category: String,
    val CategoryId: Int,
    val ContentList: Any,
    val CustomFilters: CustomFilters,
    val Filters: Any?,
    val List: String,
    val OrderOption: String,
    val ProductList: List<Product>,
    val SliderList: Any,
    val TotalItemCount: Int,
    val TotalPageCount: Int
)

class CustomFilters

data class Product(
    val ActualPriceToShowOnScreen: Double,
    val ActualPriceToShowOnScreenText: String,
    val BrandName: String,
    val CdnResizedImageUrl: String,
    val DiscountRate: Any,
    val DisplayName: String,
    val ImageUrl: String,
    val IsStrikeThroughPriceExists: Boolean,
    val Price: Double,
    val ProductId: Int,
    val StrikeThroughPriceToShowOnScreen: Double,
    val StrikeThroughPriceToShowOnScreenText: String
)