package com.example.jetpackbookreaderapp.features.register_features.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackbookreaderapp.utils.AppFonts

@Composable
fun RegisterScreen(navController: NavController) {
    Column {
        RegisterWidget()
    }
}


@Composable
fun RegisterWidget() {
    Text(
        text = "Create account",
        fontFamily = AppFonts.poppins,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp
    )
    Spacer(modifier = Modifier.height(40.dp))
    // USERNAME FIELD
    RegisterForm(title = "Username")

    // EMAIL FIELD

    // BUTTON

    // GO TO LOGIN

}

@Composable
fun RegisterForm(title: String) {
    Column() {
        Text(
            text = title, fontFamily = AppFonts.poppins,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        RegisterWidget()
    }
}