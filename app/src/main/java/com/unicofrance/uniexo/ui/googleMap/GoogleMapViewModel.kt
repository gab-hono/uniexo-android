package com.unicofrance.uniexo.ui.googleMap

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.unicofrance.uniexo.data.repositories.ContainerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GoogleMapViewModel(
    private val containerRepository: ContainerRepository,
    private val fusedLocationClient: FusedLocationProviderClient,
) : ViewModel() {
    private val _location = MutableStateFlow<LatLng?>(null)
    val location = _location.asStateFlow()

    @SuppressLint("MissingPermission")
    fun fetchUserLocation() {
        viewModelScope.launch {
            val result = fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            ).await()

            result?.let {
                _location.value = LatLng(it.latitude, it.longitude)
            }
        }
    }

}