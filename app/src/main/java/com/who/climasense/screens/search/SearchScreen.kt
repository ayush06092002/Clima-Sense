package com.who.climasense.screens.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.who.climasense.R
import com.who.climasense.navigation.ClimaScreens
import com.who.climasense.utils.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .paint(painterResource(id = R.drawable.main_bg))){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
        ) {
            Row(modifier = Modifier.padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
                ){
                Button(
                    onClick = {
                        navController.popBackStack()
                              },
                    modifier = Modifier
                        .padding(top = 10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Image(
                        painterResource(id = R.drawable.arrow_back), contentDescription = null,
                        Modifier.width(15.dp)
                    )
                }
                    TextField(
                        modifier = Modifier
                            .padding(start = 10.dp, top = 15.dp)
                            .clip(RoundedCornerShape(15.dp)),
                        value = searchText,
                        onValueChange = { searchText = it },
                        leadingIcon = {
                            Image(painterResource(id = R.drawable.search), contentDescription = null,
                                Modifier.width(20.dp))
                        },
                        placeholder = {
                            Text(text = "Enter your city here...",
                                style = TextStyle(
                                    fontFamily = fontFamily,
                                    color = Color.White,
                                ),
                                modifier = Modifier
                                    .background(Color.Transparent)
                            )
                        },
                        textStyle = TextStyle(
                            fontFamily = fontFamily,
                            color = Color.White,
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0x60FFFFFF),
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if(searchText.isNotEmpty()) {
                                    Log.d(
                                        "SearchScreen",
                                        "Search Button Clicked with text: $searchText"
                                    )
                                    navController.navigate(ClimaScreens.MainScreen.name + "/$searchText")
                                }
                            }
                        )
                    )
            }

            Spacer(modifier = Modifier.height(45.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(1){
                    CreateCitySurface()
                }
            }
        }
    }
}

@Composable
fun CreateCitySurface() {
    Surface(
        modifier = Modifier
            .width(350.dp)
            .padding(20.dp)
            .height(120.dp)
            .background(brush = Brush.verticalGradient(listOf(Color.Transparent, Color.Black))),
        shape = RoundedCornerShape(topStart = 5.dp, topEnd = 20.dp
        ,bottomStart = 20.dp, bottomEnd = 5.dp),
//        color = Color(0x60FFFFFF)
    ){
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Row(modifier = Modifier
                ) {
                    Text(
                        text = "28",
                        style = TextStyle(
                            fontFamily = fontFamily,
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Text(modifier = Modifier.padding(top = 10.dp),
                        text = "°",
                        color = Color.Yellow,
                        style = TextStyle(
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 35.sp,
                            color = Color.Yellow
                        )
                    )
                }
                Text(modifier = Modifier,
                    text = "Nagpur",
                    style = TextStyle(
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Weather Icon",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
        }
    }
}

