package com.example.mojeappka.presentation.location

import android.location.Location
import android.location.LocationManager
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.mojeappka.presentation.Dimension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.colorResource
import com.example.mojeappka.R

@Composable
fun LocationScreen(navController: NavController) {
    val context = LocalContext.current

    val locationManager = context.getSystemService(LocationManager::class.java)
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    val location: Location?

    val latitudeState = remember { mutableStateOf("Latitude: N/A") }
    val longitudeState = remember { mutableStateOf("Longitude: N/A") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(
                top = Dimension.MediumPadding1,
                start = Dimension.MediumPadding1,
                end = Dimension.MediumPadding1
            )
    ) {

        Text(
            text = "Tvoje poloha",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                id = R.color.text_title
            )
        )

        Spacer(modifier = Modifier.height(Dimension.MediumPadding1))

        Text(
            text = "Location: nic :(",
            color = colorResource(
                id = R.color.text_title
            )
        )

    }
}