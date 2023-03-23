package com.example.jetpackbookreaderapp.features.auth_features.view_model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.jetpackbookreaderapp.features.auth_features.model.UserModel
import com.example.jetpackbookreaderapp.navigations.ReaderAppScreens
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    // IN VIEW MODEL RECOMMENDED TO USING FLOW TO HANDLE VARIABLE
//    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _loading = mutableStateOf(false)
    val loading: MutableState<Boolean> = _loading

    // REGISTER USER
    fun register(email: String, password: String, context: Context, navigate: () -> Unit) =
        viewModelScope.launch {
            _loading.value = true

            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val displayName = task.result.user?.email?.split("@")?.get(0)
                            val userId = task.result.user?.uid
                            val user = UserModel(
                                userId = userId.toString(),
                                displayName = displayName.toString(),
                                avatarUrl = "",
                                quote = "live is great",
                                profession = "Software developer",
                                id = null
                            ).toMap()


                            // ADDING DATA USER TO COLLECTION IN FIREBASE FIRESTORE
                            firebaseFirestore.collection("users").add(user)
                            Toast.makeText(
                                context, "Create account success, please wait",
                                Toast.LENGTH_SHORT
                            ).show()
                            navigate()
                            _loading.value = false
                        } else {
                            Log.d("Error register", "msg: ${task.result}")
                            Toast.makeText(
                                context, "Register failed. Please check your email or password",
                                Toast.LENGTH_SHORT
                            ).show()
                            _loading.value = false
                        }
                    }

            } catch (exception: Exception) {
                Log.d("Error register", "msg: $exception")
                _loading.value = false
            }

        }

    // LOGIN USER
    fun login(email: String, password: String, context: Context, navigate: () -> Unit) =
        viewModelScope.launch {
            _loading.value = true
            try {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context, "Login success, please wait",
                            Toast.LENGTH_SHORT
                        ).show()
                        navigate()
                        _loading.value = false
                    } else {
                        Log.d("Error login", "msg: ${task.exception}")
                        Toast.makeText(
                            context, "Login failed. Please check your email or password",
                            Toast.LENGTH_SHORT
                        ).show()
                        _loading.value = false
                    }
                }

            } catch (exception: Exception) {
                Log.d("Error login", "msg: $exception")
                Toast.makeText(
                    context, "Login failed. Please check your email or password",
                    Toast.LENGTH_SHORT
                ).show()
                _loading.value = false
            }
        }

    fun logout(context: Context, navController: NavController) = viewModelScope.launch {
        _loading.value = true
        try {
            auth.signOut()
            Toast.makeText(
                context, "Logout Successfully, please wait",
                Toast.LENGTH_SHORT
            ).show()
            delay(3000L)
            navController.navigate(ReaderAppScreens.LoginScreen.name) {
                popUpTo(ReaderAppScreens.HomeScreen.name) {
                    inclusive = true
                }
            }

        } catch (e: Exception) {
            Log.d("Error login", "msg: $e")
            Toast.makeText(
                context, "Login failed. Please check your email or password",
                Toast.LENGTH_SHORT
            ).show()
            _loading.value = false
        }
    }


}

