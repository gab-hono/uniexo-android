package com.unicofrance.uniexo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.google.android.gms.location.LocationServices
import com.unicofrance.uniexo.UniExoApplication
import com.unicofrance.uniexo.ui.googleMap.GoogleMapScreen
import com.unicofrance.uniexo.ui.googleMap.GoogleMapViewModel

class MainActivity : ComponentActivity() {
    private val app by lazy { application as UniExoApplication }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            GoogleMapScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = GoogleMapViewModel(
                    containerRepository = app.containerRepository,
                    fusedLocationClient = fusedLocationClient,
                    assetManager = app.assetManager)
            )
        }
    }
}