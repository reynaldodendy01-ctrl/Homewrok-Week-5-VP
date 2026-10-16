package com.example.reyweek5.viewmodel

import com.example.reyweek5.R
import androidx.lifecycle.ViewModel
import com.example.reyweek5.model.carItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class viewModelCar: ViewModel(){

    private val _selectedCar = MutableStateFlow<carItem?>(null)

    val selectedCar: StateFlow<carItem?> = _selectedCar.asStateFlow()

    fun selectCar(car: carItem) {
        _selectedCar.value = car
    }

    fun clearSelectedCar() {
        _selectedCar.value = null
    }
    private val _carList = MutableStateFlow(
        listOf(
            carItem(
                R.drawable.tiggo,
                "Tiggo 9 CSH",
                "Auto",
                "2024",
                "5,000 km",
                "PHEV",
                "Rp 245.000.000",
                "Semarang",
                "The Tiggo 9 CSH is a premium PHEV SUV built for maximum efficiency and luxury performance.",
                true
            ),
            carItem(
                R.drawable.crv,
                "Honda CRV",
                "Auto",
                "2013",
                "15,200 km",
                "PETROL",
                "Rp 180.000.000",
                "Semarang",
                "Reliable and spacious SUV perfect for daily commuting and family trips.",
                false
            ),
            carItem(
                R.drawable.yariscross,
                "Toyota Yaris Cross",
                "Auto",
                "2024",
                "30,500 km",
                "HYBRID",
                "Rp 320.000.000",
                "Semarang",
                "Modern hybrid crossover offering great fuel economy and smart driver assistance features.",
                true
            ),
            carItem(
                R.drawable.hrv,
                "Honda HRV",
                "Auto",
                "2023",
                "12,000 km",
                "PETROL",
                "Rp 350.000.000",
                "Semarang",
                "Stylish crossover SUV with modern design and smooth performance.",
                false
            ),
            carItem(
                R.drawable.j5,
                "JAC J5",
                "Auto",
                "2024",
                "2,500 km",
                "EV",
                "Rp 410.000.000",
                "Semarang",
                "All-electric sedan delivering clean drive and cutting-edge tech.",
                false
            ),
            carItem(
                R.drawable.mg4,
                "MG 4 EV",
                "Auto",
                "2023",
                "8,400 km",
                "EV",
                "Rp 425.000.000",
                "Semarang",
                "Futuristic hatchback EV with impressive range and sharp styling.",
                false
            ),
            carItem(
                R.drawable.reborn,
                "Innova Reborn",
                "Manual",
                "2021",
                "45,000 km",
                "DIESEL",
                "Rp 365.000.000",
                "Semarang",
                "Comfortable and durable MPV known for strong diesel engine power.",
                false
            ),
            carItem(
                R.drawable.ayla,
                "Daihatsu Ayla",
                "Auto",
                "2022",
                "18,000 km",
                "PETROL",
                "Rp 135.000.000",
                "Semarang",
                "Compact city car ideal for navigating tight urban traffic easily.",
                false
            ),
            carItem(
                R.drawable.xenia,
                "Daihatsu Xenia",
                "Manual",
                "2022",
                "28,000 km",
                "PETROL",
                "Rp 195.000.000",
                "Semarang",
                "Practical 7-seater MPV designed for everyday family use and long journeys.",
                false
            ),
            carItem(
                R.drawable.yaris_cross,
                "Toyota Yaris",
                "Auto",
                "2021",
                "52,400 km",
                "PETROL",
                "Rp 245.000.000",
                "Semarang",
                "The Toyota Yaris G AT is a sporty compact hatchback that punches above its weight class. Equipped with a 1.5L dual VVT-i engine, 7 airbags, pre-collision system, and a floating head unit, it offers premium features at a very accessible price point.",
                true
            )
        )
    )

    val carList: StateFlow<List<carItem>> = _carList.asStateFlow()

    fun toggleFavorite(car: carItem) {
        _carList.update { list ->
            list.map { item ->
                if (item.name == car.name) {
                    item.copy(isFavorite = !item.isFavorite)
                } else {
                    item
                }
            }
        }
        if (_selectedCar.value?.name == car.name) {
            _selectedCar.update { it?.copy(isFavorite = !it.isFavorite) }
        }
    }

//    fun navigateToDetail(car: carItem){
//
//    }
}