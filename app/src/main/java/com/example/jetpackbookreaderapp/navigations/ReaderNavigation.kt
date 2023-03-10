package com.example.jetpackbookreaderapp.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackbookreaderapp.features.detail_book_feature.view.DetailBookScreen
import com.example.jetpackbookreaderapp.features.home_fature.view.HomeScreen
import com.example.jetpackbookreaderapp.features.login_features.view.LoginScreen
import com.example.jetpackbookreaderapp.features.profile_feature.view.ProfileScreen
import com.example.jetpackbookreaderapp.features.register_features.view.RegisterScreen
import com.example.jetpackbookreaderapp.features.search_feature.view.SearchBookScreen
import com.example.jetpackbookreaderapp.features.splash_screen_feature.view.SplashScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ReaderAppScreens.SplashScreen.name){
        // DEFINE ALL POSSIBLE SCREEN THAT APP WILL HAVE

        composable(ReaderAppScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(ReaderAppScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(ReaderAppScreens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }

        composable(ReaderAppScreens.RegisterScreen.name) {
            RegisterScreen(navController = navController)
        }

        composable(ReaderAppScreens.ProfileScreen.name) {
            ProfileScreen(navController = navController)
        }

        composable(ReaderAppScreens.SearchScreen.name) {
            SearchBookScreen(navController = navController)
        }

        composable(ReaderAppScreens.DetailBookScreen.name) {
            DetailBookScreen(navController = navController)
        }

    }
}