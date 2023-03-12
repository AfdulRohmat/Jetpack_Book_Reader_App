package com.example.jetpackbookreaderapp.features.auth_features.view_model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackbookreaderapp.features.auth_features.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    // IN VIEW MODEL RECOMMENDED TO USING FLOW TO HANDLE VARIABLE
//    val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    // REGISTER USER
    fun register(email: String, password: String, context: Context, navigate: () -> Unit) =
        viewModelScope.launch {
            if (_loading.value == false) {
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
                                _loading.value = false
                                Toast.makeText(
                                    context, "Register failed. Please check your email or password",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                } catch (exception: Exception) {
                    Log.d("Error register", "msg: $exception")
                    _loading.value = false
                }
            }

        }

    // LOGIN USER
    fun login(email: String, password: String, context: Context, navigate: () -> Unit) =
        viewModelScope.launch {
            if (_loading.value == false) {
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
                            _loading.value = false
                            Toast.makeText(
                                context, "Login failed. Please check your email or password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                } catch (exception: Exception) {
                    Log.d("Error login", "msg: $exception")
                    _loading.value = false
                }
            }

        }


}

