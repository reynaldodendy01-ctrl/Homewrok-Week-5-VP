package com.example.reyweek5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reyweek5.ui.screen.CarListPerCarScreen
import com.example.reyweek5.ui.screen.CarListScreen
import com.example.reyweek5.ui.theme.Reyweek5Theme
import com.example.reyweek5.viewmodel.viewModelCar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Reyweek5Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp(
    viewModelCar: viewModelCar = viewModel()
) {
    val selectedCar by viewModelCar.selectedCar.collectAsState()

    val car = selectedCar
    if (car != null) {
        CarListPerCarScreen(
            car = car,
            onBackClick = {
                viewModelCar.clearSelectedCar()
            },
            onFavoriteClick = { carItem ->
                viewModelCar.toggleFavorite(carItem)
            }
        )
    } else {
        CarListScreen(
            viewModelCar = viewModelCar,
            onCarClick = { carItem ->
                viewModelCar.selectCar(carItem)
            }
        )
    }
}
