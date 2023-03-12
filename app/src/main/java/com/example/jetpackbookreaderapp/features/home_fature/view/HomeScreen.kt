package com.example.jetpackbookreaderapp.features.home_fature.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackbookreaderapp.R
import com.example.jetpackbookreaderapp.navigations.ReaderAppScreens
import com.example.jetpackbookreaderapp.utils.AppColors
import com.example.jetpackbookreaderapp.utils.AppFonts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            HomeLayout(navController = navController)


        }
    }
}

@Composable
fun HomeLayout(navController: NavController) {
    val search = rememberSaveable() { mutableStateOf("") }
    val auth: FirebaseAuth = Firebase.auth

    // SECTION ACCOUNT AND LOGOUT
    HeaderSection(
        displayName = if (auth.currentUser?.email.isNullOrEmpty()) "" else auth.currentUser?.email?.split(
            "@"
        )?.get(0),
        logout = {}, navController = navController
    )

    // SECTION MOTIVATE AND SEARCHING
    SearchBookSection(searchState = search, onAction = KeyboardActions {
        // SEARCH FUNCTIONALITY LATER
    })

    // BANNER ROW SCROLL
    BannerSection()

    // SECTION RECENT READ

    // SECTION READING LIST

    // SECTION TRENDING BOOK
}

@Composable
fun BannerSection(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(18.dp))
    Surface(
        elevation = 4.dp,
        modifier = modifier
            .height(180.dp)
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .background(color = Color.White),
        shape = RoundedCornerShape(8.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner_book_1),
            contentDescription = "banner_1",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun SearchBookSection(
    modifier: Modifier = Modifier,
    searchState: MutableState<String>,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Password,
    onAction: KeyboardActions = KeyboardActions.Default,

    ) {
    Spacer(modifier = modifier.height(16.dp))
    Text(
        text = "Keep exploring",
        fontFamily = AppFonts.poppins,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        overflow = TextOverflow.Ellipsis,
    )
    Spacer(modifier = modifier.height(4.dp))

    // Search Book
    Surface(
        elevation = 4.dp,
        modifier = modifier
            .height(64.dp)
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .background(color = Color.White),
        shape = RoundedCornerShape(16.dp),
    ) {
        OutlinedTextField(
            value = searchState.value, onValueChange = { searchState.value = it },
            placeholder = { Text(text = "Search book..") },
            singleLine = true,
            textStyle = TextStyle(fontSize = 12.sp, color = Color.Gray),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = onAction,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search",
                    tint = Color.Gray
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
            )

        )
    }

}

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    logout: () -> Unit,
    displayName: String?,
    navController: NavController,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // ROW ACCOUNT AND NAME
        Row(
            modifier = Modifier
                .weight(2f)
                .clickable {
                    navController.navigate(ReaderAppScreens.ProfileScreen.name)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(45.dp)
                    .width(45.dp)
                    .background(color = AppColors.mGray, shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Hello, $displayName",
                fontFamily = AppFonts.poppins,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }

        // LOGOUT
        IconButton(
            onClick = {
                logout()
            }, modifier = Modifier
                .height(45.dp)
                .width(45.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Logout,
                contentDescription = "logout",
                tint = Color.Black
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
//            HomeLayout(navController)
        }
    }
}