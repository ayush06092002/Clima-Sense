package com.who.climasense.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.who.climasense.R
import com.who.climasense.models.Favorites
import com.who.climasense.screens.favorite.FavoriteViewModel
import com.who.climasense.utils.fontFamily
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun CityDisplay(
    city: String,
    icon: Int = R.drawable.ic_launcher_foreground,
    temp: String,
    favViewModel: FavoriteViewModel
) {
    Column(modifier = Modifier
        .padding(start = 25.dp, top = 20.dp)) {
        Text(
            text = "Today",
            style = TextStyle(
                fontFamily = fontFamily,
                fontSize = 16.sp,
                color = Color.White
            )
        )
        //add label
        Row(horizontalArrangement = Arrangement.Center) {
            Text(modifier = Modifier.padding(bottom = 5.dp),
                text = city,
                style = TextStyle(
                    fontFamily = fontFamily,
                    fontSize = 32.sp,
                    color = Color.White,
                )
            )
            Spacer(modifier = Modifier.width(10.dp))


            if(favViewModel.isFavorite(city)){
                Image(painter = painterResource(id = R.drawable.favorite), contentDescription = "Favorite",
                    modifier = Modifier.width(30.dp).padding(top = 8.dp)
                        .clickable {
                            favViewModel.deleteFavorite(city)
                        })
            } else{
                Image(painter = painterResource(id = R.drawable.favorite_border), contentDescription = "Not Favorite",
                    modifier = Modifier.width(30.dp).padding(top = 8.dp)
                        .clickable {
                            favViewModel.addFavorite(Favorites(
                                city = city,
                                temp = temp,
                                icon = icon
                            ))
                        })
            }
        }

        Text(
            text = SimpleDateFormat("dd MMMM yyyy").format(Date()),
            style = TextStyle(
                fontFamily = fontFamily,
                fontSize = 12.sp,
                color = Color.White,
            )
        )
    }
}