package com.example.jetpackbookreaderapp.navigations

enum class ReaderAppScreens {
    SplashScreen,
    HomeScreen,
    LoginScreen,
    RegisterScreen,
    SearchScreen,
    DetailBookScreen,
    UpdateScreen,
    ReaderStatsScreen,
    ProfileScreen,
    ;

    companion object {
        fun fromRoute(route: String?): ReaderAppScreens =
            when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                HomeScreen.name -> HomeScreen
                LoginScreen.name -> LoginScreen
                RegisterScreen.name -> RegisterScreen
                SearchScreen.name -> SearchScreen
                DetailBookScreen.name -> DetailBookScreen
                UpdateScreen.name -> UpdateScreen
                ProfileScreen.name -> ProfileScreen
                ReaderStatsScreen.name -> ReaderStatsScreen
                null -> HomeScreen
                else -> throw java.lang.IllegalArgumentException("Route $route is not defined")
            }
    }
}