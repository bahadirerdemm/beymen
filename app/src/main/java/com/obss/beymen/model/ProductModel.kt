package com.obss.beymen.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductModel(
    val Exception: Any,
    val IsOriginFromCheckout: Boolean,
    val Message: Any,
    val Result: ProductResult,
    val Success: Boolean
)

data class ProductResult(
    val ActualPrice: Int,
    val ActualPriceText: String,
    val BrandLink: BrandLink,
    val BrandName: String,
    val BreadcrumbCategory: String,
    val CategoryId: Int,
    val CategoryLink: CategoryLink,
    val CategoryName: String,
    val ContentUrl: ContentUrl,
    val Description: HashMap<String,String>,
    val DiscountRateText: Any,
    val DisplayName: String,
    val EstimatedSupplyDate: Any,
    val ExternalSystemCode: String,
    val GtmModel: GtmModel,
    val HasHopiParacik: Boolean,
    val HasQuantitySelector: Boolean,
    val HopiParacikText: Any,
    val Images: List<Image>,
    val Installment: Installment,
    val IsBanned: Boolean,
    val IsCosmeticProduct: Boolean,
    val IsGiftCard: Boolean,
    val IsPreOrder: Boolean,
    val IsStrikeThroughPriceExist: Boolean,
    val IsVatIncluded: Boolean,
    val List: String,
    val OtherColorList: List<OtherColor>,
    val ProductId: Int,
    val ShareUrl: String,
    val SizeSystem: Any,
    val StrikeThroughPrice: Int,
    val StrikeThroughPriceText: Any,
    val VatRate: Int
)

data class BrandLink(
    val DisplayName: String,
    val Url: String
)

data class CategoryLink(
    val DisplayName: String,
    val Url: String
)

data class ContentUrl(
    val Delivery: String,
    val OneCard: String,
    val Shipment: String,
    val ShipmentAndDelivery: Any,
    val SizeChartUrl: String
)

data class Description(
    @param:JsonProperty("Kumaş ve Bakım")
    @get:JsonProperty("Kumaş ve Bakım")
    val kumas_ve_bakim: String,

    @param:JsonProperty("Yıl Sezon")
    @get:JsonProperty("Yıl Sezon")
    val yil_sezon: String,

    @param:JsonProperty("Özellikler")
    @get:JsonProperty("Özellikler")
    val ozellikler: String,

    @param:JsonProperty("Ürün Kodu")
    @get:JsonProperty("Ürün Kodu")
    val urun_kodu: String
)

data class GtmModel(
    val Pconsignment: String,
    val Pdsct: String,
    val Pgen: String,
    val Pmaingroup: String,
    val Pother: String,
    val Pproductgroup: String,
    val Pseas: String
)

data class Image(
    val DisplayOrder: Int,
    val Images: List<ImageX>
)

data class Installment(
    val HasInstallmentWarning: Boolean,
    val InstallmentCount: Int,
    val InstallmentWarningText: Any,
    val Url: String
)

data class OtherColor(
    val ColorName: String,
    val ImageUrl: String,
    val IsSelected: Boolean,
    val ProductId: Int
)

data class ImageX(
    val ImageUrl: String,
    val SizeCode: String
)