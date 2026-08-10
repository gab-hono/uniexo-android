package com.unicofrance.uniexo.ui.googleMap

import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.unicofrance.uniexo.data.repositories.ContainerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GoogleMapViewModel(
    private val containerRepository: ContainerRepository,
) : ViewModel() {
    private val _location = MutableStateFlow<LatLng?>(null)
    val location = _location.asStateFlow()

}