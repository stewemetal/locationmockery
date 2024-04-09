package com.stewemetal.takehometemplate.map.ui

import com.stewemetal.takehometemplate.map.model.Location

sealed interface MapViewEvent {
    data class LocationSelected(
        val selectedLocation: Location,
    ) : MapViewEvent
}
