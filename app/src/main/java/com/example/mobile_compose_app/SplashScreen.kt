package com.example.mobile_compose_app

import android.view.animation.OvershootInterpolator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun ShowSplashScreen(navController: NavController) {
    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.6f,
            animationSpec = tween(
                durationMillis=1500,
                easing={
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate("login")
    }
    Surface(
        modifier= Modifier.fillMaxSize(),
        color= Color(0xFF293245)
    ){
        Image(
            painter= painterResource(id = R.drawable.small_logo),
            modifier=Modifier
                .scale(scale.value),
            contentDescription="Logo"
        )
    }
}