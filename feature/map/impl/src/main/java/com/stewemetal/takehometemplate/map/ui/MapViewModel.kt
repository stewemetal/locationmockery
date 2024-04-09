package com.stewemetal.takehometemplate.map.ui

import com.stewemetal.takehometemplate.map.model.Location
import com.stewemetal.takehometemplate.shell.architecture.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MapViewModel : BaseViewModel<MapViewEvent, MapState>(
    MapState()
) {
    override fun onViewEvent(event: MapViewEvent) {
        when (event) {
            is MapViewEvent.LocationSelected -> {
                emitNewState {
                    copy(
                        selectedLocation = Location(
                            event.selectedLocation.lat,
                            event.selectedLocation.lon,
                        )
                    )
                }
            }
        }
    }

    fun onLocationSelected(selectedLocation: Location) {
        emitNewState {
            copy(
                selectedLocation = Location(
                    selectedLocation.lat,
                    selectedLocation.lon,
                )
            )
        }
    }

    fun onActivateMockLocation() {
        emitNewState {
            copy(
                mockLocation = selectedLocation,
            )
        }
    }
}
