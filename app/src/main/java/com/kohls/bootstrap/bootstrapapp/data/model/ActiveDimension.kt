package com.kohls.bootstrap.bootstrapapp.data.model

data class ActiveDimension(
    val activeDimensionValues: List<ActiveDimensionValue>,
    val index: Int,
    val label: String,
    val name: String
)