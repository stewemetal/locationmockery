package com.stewemetal.takehometemplate.map.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun MapScreen() {
    val viewModel: MapViewModel = koinViewModel()
    val state = viewModel.state.collectAsState()

    val context = LocalContext.current
    MapScreenImpl(
        state = state.value,
        onLocationSelected = {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
            viewModel.onLocationSelected(it)
        },
        onActivateMockLocation = viewModel::onActivateMockLocation,
    )
}
