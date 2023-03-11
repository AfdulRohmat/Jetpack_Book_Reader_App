package com.example.jetpackbookreaderapp.features.login_features.view

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackbookreaderapp.features.auth_features.view.components.EmailInput
import com.example.jetpackbookreaderapp.features.auth_features.view.components.InputField
import com.example.jetpackbookreaderapp.features.auth_features.view.components.PasswordInput
import com.example.jetpackbookreaderapp.features.register_features.view.RegisterWidget
import com.example.jetpackbookreaderapp.features.splash_screen_feature.view.SplashScreenWidget
import com.example.jetpackbookreaderapp.utils.AppColors
import com.example.jetpackbookreaderapp.utils.AppFonts

@Composable
fun LoginScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            LoginWidget()
        }
    }

}

@Composable
fun LoginWidget() {
    Text(
        text = "Login",
        fontFamily = AppFonts.poppins,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp
    )
    Text(
        text = "Welcome back !. Please login to back to your app",
        fontFamily = AppFonts.poppins,
        fontSize = 14.sp,
        color = Color.Black.copy(alpha = 0.8F)
    )
    Spacer(modifier = Modifier.height(40.dp))
    LoginSection()
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Don’t have an account ? ",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            fontFamily = AppFonts.poppins,
            color = Color.Black.copy(alpha = 0.5F)
        )
        TextButton(onClick = {}) {
            Text(
                text = "Register",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                fontFamily = AppFonts.poppins,
                color = Color.Black
            )
        }


    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginSection(
    isLoading: Boolean = false,
    onDone: (String, String) -> Unit = { email, password -> }
) {
    // USING rememberSaveable() to save state even the screen get rotate
    val email = rememberSaveable() { mutableStateOf("") }
    val password = rememberSaveable() { mutableStateOf("") }
    val showPassword = rememberSaveable() { mutableStateOf(false) }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val isValidEmailOrPassword = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val modifierColum = Modifier
        .background(MaterialTheme.colors.background)
        .verticalScroll(
            rememberScrollState()
        )

    Column(
        modifier = modifierColum,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailInput(
            emailState = email,
            isEnable = true,
            onAction = KeyboardActions {
                passwordFocusRequest.requestFocus()
            }
        )

        PasswordInput(
            passwordState = password,
            passwordVisible = showPassword,
            onAction = KeyboardActions {
                if (!isValidEmailOrPassword) return@KeyboardActions
                onDone(email.value, password.value)
            },
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text(
                text = "Login",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                fontFamily = AppFonts.poppins,
                color = Color.White
            )
        }


    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            LoginWidget()
        }
    }
}