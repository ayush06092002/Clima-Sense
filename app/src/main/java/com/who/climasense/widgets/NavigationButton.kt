package com.who.climasense.widgets

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.who.climasense.screens.NavigationPane
import com.who.climasense.utils.noRippleClickable

@Preview
@Composable
fun CreateNavigationButton() {
    var isNavigationVisible by remember { mutableStateOf(false) }
        Column(modifier = Modifier
            .padding(start = 25.dp, top = 14.dp)) {
            Button(onClick = {
                isNavigationVisible = !isNavigationVisible
                Log.d("CreateNavigationButton", "Navigation Button Clicked"
                )
            },
                modifier = Modifier
                    .height(3.dp)
                    .width(40.dp),
                colors = ButtonDefaults.buttonColors(Color.Yellow)) {

            }
            Spacer(modifier = Modifier.height(4.dp))
            Button(onClick = {
                isNavigationVisible = !isNavigationVisible
                Log.d("CreateNavigationButton", "Navigation Button Clicked")
            },
                modifier = Modifier
                    .height(3.dp)
                    .width(23.dp),
                colors = ButtonDefaults.buttonColors(Color.Yellow)) {

            }
        }
    AnimatedVisibility(
        visible = isNavigationVisible,
        enter = slideInHorizontally(initialOffsetX = { -it }),
        exit = slideOutHorizontally(targetOffsetX = { -it })
    ) {
        NavigationPane()
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 160.dp)
                .noRippleClickable{
                    isNavigationVisible = false
                },
            color = Color.Transparent
        ){
        }
    }
}
