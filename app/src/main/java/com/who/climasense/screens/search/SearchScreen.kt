package com.who.climasense.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.who.climasense.R
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
                .background(Color.Transparent)
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.width(350.dp),
                placeholder = {
                    Text(text = "Enter your city here",
                        style = TextStyle(
                            fontFamily = fontFamily,
                            color = Color.White,
                        ),
                        modifier = Modifier.padding(start = 10.dp)
                    )
                },
                textStyle = TextStyle(
                    fontFamily = fontFamily,
                    color = Color.White,
                ),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                )
            )
            Button(
                onClick = { /* Handle search button click */ },
                modifier = Modifier.width(100.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Image(painterResource(id = R.drawable.search), contentDescription = null )
            }
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.width(100.dp).padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Image(painterResource(id = R.drawable.arrowback), contentDescription = null )
            }
        }
    }
}
