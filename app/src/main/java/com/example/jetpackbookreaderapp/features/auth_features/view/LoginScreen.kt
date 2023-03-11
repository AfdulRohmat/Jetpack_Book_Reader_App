package com.example.jetpackbookreaderapp.features.login_features.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetpackbookreaderapp.features.auth_features.view.components.EmailInput
import com.example.jetpackbookreaderapp.features.auth_features.view.components.PasswordInput
import com.example.jetpackbookreaderapp.features.auth_features.view_model.AuthViewModel
import com.example.jetpackbookreaderapp.navigations.ReaderAppScreens
import com.example.jetpackbookreaderapp.utils.AppColors
import com.example.jetpackbookreaderapp.utils.AppFonts

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            LoginWidget(navController = navController, authViewModel)
        }
    }

}

@Composable
fun LoginWidget(navController: NavController, authViewModel: AuthViewModel) {
    val mContext = LocalContext.current

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
    LoginSection(isLoading = false,
        onDone = { email, password ->
            authViewModel.login(email, password, context = mContext, navigate = {
                navController.navigate(ReaderAppScreens.HomeScreen.name)
            })

        })
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
        TextButton(onClick = {
            navController.navigate(ReaderAppScreens.RegisterScreen.name)
        }) {
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
            isEnable = !isLoading,
            onAction = KeyboardActions {
                passwordFocusRequest.requestFocus()
            }
        )

        PasswordInput(
            passwordState = password,
            passwordVisible = showPassword,
            isEnable = !isLoading,
            onAction = KeyboardActions {
                if (!isValidEmailOrPassword) return@KeyboardActions
                onDone(email.value, password.value)
            },
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                onDone(email.value.trim(), password.value.trim())
                keyboardController?.hide()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppColors.mDarkBlue,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = !isLoading && isValidEmailOrPassword
        ) {
            if (isLoading) CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.size(16.dp)
            ) else Text(
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

//            LoginWidget(navController)
        }
    }
}