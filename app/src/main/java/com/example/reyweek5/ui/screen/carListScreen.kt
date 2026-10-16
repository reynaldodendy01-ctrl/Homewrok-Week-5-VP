package com.example.reyweek5.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reyweek5.model.carItem
import com.example.reyweek5.viewmodel.viewModelCar
import com.example.reyweek5.R
import androidx.compose.foundation.clickable

@Composable
fun CarListScreen(
    viewModelCar: viewModelCar = viewModel(),
    onCarClick: (carItem) -> Unit = {}
) {
    val carList by viewModelCar.carList.collectAsState()
    val favoriteCount = carList.count { it.isFavorite }

    Box(Modifier
        .fillMaxSize()
        .background(Color(0xFFF6F4F0))){
        LazyColumn(Modifier
            .padding(20.dp)
            .fillMaxSize()) {
                item{
                Row(Modifier.padding(top=20.dp)) {
                    HorizontalDivider(
                        modifier = Modifier.width(43.dp),
                        thickness = 4.dp,
                        color = Color(0xFFFF5636)
                    )
                }

            Row(Modifier.fillMaxWidth()) {
                Column(Modifier
                    .weight(1f)
                    .padding(top = 5.dp)) {
                    Row() {
                        Text("On Sale", fontSize = 40.sp, fontWeight = FontWeight.Bold, color=Color.Black)
                    }
                    Row() {
                        Text("Find Your Perfect Ride", color= Color.Gray)
                    }

                }
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, Color(0xFFFFCBBE), RoundedCornerShape(50.dp))
                        .background(Color(0xFFFFEBEA))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .weight(0.4f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row() {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            tint = Color(0xFFFF5636),
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "$favoriteCount saved",
                            color = Color(0xFFFF5636),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Row() {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 20.dp),
                    thickness = 1.dp,
                    color = Color.Gray
                )
            }}
            items(carList) { car ->
                CarCard(
                    car = car,
                    onFavoriteClick = {
                        viewModelCar.toggleFavorite(car)
                    },
                    onCardClick = {
                        viewModelCar.selectCar(car)
                        onCarClick(car)
                    }
                )
            }
//            LazyColumn() {
//
//            }
        }
    }
}

@Composable
fun CarCard(
    car: carItem,
    onFavoriteClick: () -> Unit,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(car.imageRes),
                    contentDescription = car.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(6.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFFDDF0E7))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = car.fueltype,
                        color = Color(0xFF1E5B42),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 4.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = car.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A),
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = onFavoriteClick,
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = if (car.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (car.isFavorite) Color(0xFFFF5636) else Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF4F3EF))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = car.transmission,
                        color = Color(0xFF64748B),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xFFFF5636))
                        )
                        Text(
                            text = car.year,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A)
                        )
                    }

                    Text(
                        text = car.mileage,
                        fontSize = 13.sp,
                        color = Color(0xFF64748B),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CarListScreenPreview() {
    CarListScreen()
}