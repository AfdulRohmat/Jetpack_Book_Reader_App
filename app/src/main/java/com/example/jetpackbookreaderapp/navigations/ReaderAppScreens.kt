package com.example.jetpackbookreaderapp.navigations

enum class ReaderAppScreens {
    SplashScreen,
    HomeScreen,
    LoginScreen,
    RegisterScreen,
    SearchScreen,
    DetailScreen,
    UpdateScreen,
    ReaderStatsScreen;

    companion object {
        fun fromRoute(route: String?): ReaderAppScreens =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                HomeScreen.name -> HomeScreen
                LoginScreen.name -> LoginScreen
                RegisterScreen.name -> RegisterScreen
                SearchScreen.name -> SearchScreen
                DetailScreen.name -> DetailScreen
                UpdateScreen.name -> UpdateScreen
                ReaderStatsScreen.name -> ReaderStatsScreen
                null -> HomeScreen
                else -> throw java.lang.IllegalArgumentException("Route $route is not defined")
            }
    }
}