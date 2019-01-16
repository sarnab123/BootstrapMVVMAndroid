package com.kohls.bootstrap.bootstrapapp.data.model

data class DimensionValue(
    val ID: String,
    val active: Boolean,
    val currentDimensionId: String,
    val index: Int,
    val name: String,
    val productCount: Int,
    val seoURL: String
)