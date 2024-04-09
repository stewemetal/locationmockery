package com.stewemetal.takehometemplate.map.ui

import com.stewemetal.takehometemplate.map.model.Location

data class MapState(
    val isLoading: Boolean = false,
    val selectedLocation: Location? = null,
    val mockLocation: Location? = null,
)
