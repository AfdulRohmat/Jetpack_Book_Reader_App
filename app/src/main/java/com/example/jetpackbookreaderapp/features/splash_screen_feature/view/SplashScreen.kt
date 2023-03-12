package com.example.jetpackbookreaderapp.features.splash_screen_feature.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackbookreaderapp.navigations.ReaderAppScreens
import com.example.jetpackbookreaderapp.utils.AppFonts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val auth: FirebaseAuth = Firebase.auth

    // DELAY FOR SEVERAL TIME THEN GO TO NEXT PAGE
    LaunchedEffect(key1 = true, block = {
        delay(4000L)
//        navController.navigate(ReaderAppScreens.LoginScreen.name) {
//            popUpTo(ReaderAppScreens.SplashScreen.name) {
//                inclusive = true
//            }
//        }
        if (auth.currentUser?.email.isNullOrEmpty()) navController.navigate(ReaderAppScreens.LoginScreen.name) {
            popUpTo(ReaderAppScreens.SplashScreen.name) {
                inclusive = true
            }
        } else navController.navigate(ReaderAppScreens.HomeScreen.name) {
            popUpTo(ReaderAppScreens.SplashScreen.name) {
                inclusive = true
            }
        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SplashScreenWidget()
    }
}

@Composable
fun SplashScreenWidget() {
    Image(
        painter = painterResource(id = com.example.jetpackbookreaderapp.R.drawable.splash_image),
        contentDescription = "icon_app",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(260.dp),
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = "Keep Reading",
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        fontFamily = AppFonts.poppins,
        color = Color.Black
    )
    Text(
        text = "You’ll fall in love",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        fontFamily = AppFonts.poppins,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(48.dp))
    Text(
        text = "A library of bite-sized business eBooks on soft skills and access to 30+ millions books in various languages with an easy and simple monthly subscription and read anywhere, any devices.",
        modifier = Modifier.padding(horizontal = 24.dp),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        fontFamily = AppFonts.poppins,
        color = Color.Black,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(48.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SplashScreenWidget()
    }
}