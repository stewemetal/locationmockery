package com.stewemetal.takehometemplate.map.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mapbox.geojson.Point
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.ViewAnnotationAnchor
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.annotation.ViewAnnotation
import com.mapbox.maps.viewannotation.annotationAnchor
import com.mapbox.maps.viewannotation.geometry
import com.mapbox.maps.viewannotation.viewAnnotationOptions
import com.stewemetal.takehometemplate.map.model.Location
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, MapboxExperimental::class)
@Composable
fun MapScreenImpl(
    state: MapState,
    onLocationSelected: (Location) -> Unit,
    onActivateMockLocation: () -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Login")
        })
    }) { paddingValues ->
        MapboxMap(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            mapViewportState = MapViewportState().apply {
                setCameraOptions {
                    zoom(10.0)
                    center(Point.fromLngLat(-98.0, 39.5))
                    pitch(0.0)
                    bearing(0.0)
                }
            },
            onMapClickListener = {
                onLocationSelected(
                    Location(
                        lat = it.latitude(),
                        lon = it.longitude(),
                    )
                )
                true
            }
        ) {
            state.selectedLocation?.run {
                ViewAnnotation(
                    options = viewAnnotationOptions {
                        geometry(Point.fromLngLat(lon, lat))
                        annotationAnchor {
                            anchor(ViewAnnotationAnchor.BOTTOM)
                        }
                        allowOverlap(false)
                    }
                ) {
                    Column(
                        horizontalAlignment = CenterHorizontally,
                    ) {
                        Button(
                            onClick = onActivateMockLocation,
                        ) {
                            Text("Set mock location")
                        }
                        Image(
                            painter = painterResource(id = android.R.drawable.arrow_down_float),
                            contentDescription = "",
                            modifier = Modifier
                                .size(20.dp),
                            colorFilter = ColorFilter.tint(Color.Blue)
                        )
                    }
                }
            }

            state.mockLocation?.run {
                ViewAnnotation(
                    options = viewAnnotationOptions {
                        geometry(Point.fromLngLat(lon, lat))
                        annotationAnchor {
                            anchor(ViewAnnotationAnchor.CENTER)
                        }
                        allowOverlap(false)
                    }
                ) {
                    Image(
                        painter = painterResource(
                            id = android.R.drawable.ic_menu_close_clear_cancel
                        ),
                        contentDescription = "Mock location set",
                        modifier = Modifier
                            .size(40.dp),
                        colorFilter = ColorFilter.tint(Color.Red)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenImplPreview() {
    var state by remember { mutableStateOf(MapState()) }

    LaunchedEffect(state.isLoading) {
        if (state.isLoading) {
            delay(1000)
            state = state.copy(isLoading = false)
        }
    }

}
