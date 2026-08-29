package com.unicofrance.uniexo.ui.googleMap

import android.annotation.SuppressLint
import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.unicofrance.uniexo.data.local.csv.CsvParser
import com.unicofrance.uniexo.data.repositories.ContainerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class GoogleMapViewModel(
    private val containerRepository: ContainerRepository,
    private val fusedLocationClient: FusedLocationProviderClient,
    private val assetManager: AssetManager,
) : ViewModel() {
    private val _location = MutableStateFlow<LatLng?>(null)
    val location = _location.asStateFlow()

    val containers = containerRepository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        loadContainersIfNeeded()
    }

    private fun loadContainersIfNeeded() {
        viewModelScope.launch(Dispatchers.IO) {
            if (containerRepository.count() == 0) {
                val csvContent = assetManager.open("containers.csv")
                    .bufferedReader()
                    .use { it.readText() }

                val containers = CsvParser.parseContainers(csvContent)
                containerRepository.insertAll(containers)
            }
        }
    }

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