package com.kohls.bootstrap.bootstrapapp.data.model

data class Price(
    val displayBegDateTime: String,
    val displayEndDateTime: String,
    val isCurrentPrice: Boolean,
    val isPriceInstore: Boolean,
    val isSuppressed: Boolean,
    val priceCode: String,
    val promotion: Any,
    val promotions: Promotions,
    val purchaseBegDateTime: String,
    val purchaseEndDateTime: String,
    val regularPrice: RegularPrice,
    val regularPriceType: String,
    val salePrice: SalePrice,
    val salePriceStatus: String,
    val salePriceType: Any,
    val statusCode: String,
    val suppressedPricingText: Any
)