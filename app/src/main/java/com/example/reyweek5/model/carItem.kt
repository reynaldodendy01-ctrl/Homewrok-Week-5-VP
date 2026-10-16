package com.example.reyweek5.model

import androidx.annotation.DrawableRes
import org.w3c.dom.Text

data class carItem (
        @DrawableRes val imageRes: Int,
        val name: String,
        val transmission: String,
        val year: String,
        val mileage: String,
        val fueltype: String,
        val price: String,
        val location: String,
        val about: String,
        val isFavorite: Boolean
)