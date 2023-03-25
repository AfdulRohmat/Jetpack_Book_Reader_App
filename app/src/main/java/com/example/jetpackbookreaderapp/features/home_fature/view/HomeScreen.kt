package com.example.jetpackbookreaderapp.features.home_fature.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetpackbookreaderapp.features.auth_features.view_model.AuthViewModel
import com.example.jetpackbookreaderapp.features.detail_book_feature.model.BookModel
import com.example.jetpackbookreaderapp.features.home_fature.view.components.CardBanner
import com.example.jetpackbookreaderapp.features.home_fature.view.components.CardBookItem
import com.example.jetpackbookreaderapp.features.home_fature.view_model.HomeViewModel
import com.example.jetpackbookreaderapp.navigations.ReaderAppScreens
import com.example.jetpackbookreaderapp.utils.AppColors
import com.example.jetpackbookreaderapp.utils.AppFonts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val mContext = LocalContext.current
    var booksFromFirestore = listOf<BookModel>()
    val currentUser = FirebaseAuth.getInstance().currentUser
    var openDialog = remember { mutableStateOf(false) }

    // call once when the view appears
    LaunchedEffect(Unit, block = {
        homeViewModel.getAllBooksFromFirestore()
    })

    if (!homeViewModel.booksFromFirestoreData.value.data.isNullOrEmpty()) {
        booksFromFirestore =
            homeViewModel.booksFromFirestoreData.value.data!!.toList().filter { book ->
                book.userId == currentUser?.uid.toString()
            }
        Log.d("Books", booksFromFirestore.toString())
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            val search = rememberSaveable() { mutableStateOf("") }
            val auth: FirebaseAuth = Firebase.auth

            // SECTION ACCOUNT AND LOGOUT
            HeaderSection(
                displayName = if (auth.currentUser?.email.isNullOrEmpty()) "" else auth.currentUser?.email?.split(
                    "@"
                )?.get(0),
                logout = {
                    openDialog.value = true
                }, navController = navController
            )

            // OPEN / CLOSE ALERT DIALOG
            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = { openDialog.value = false },
                    buttons = {

                        Row(
                            modifier = Modifier
                                .padding(all = 8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                modifier = Modifier.width(60.dp),
                                onClick = { openDialog.value = false }
                            ) {
                                Text("No")
                            }
                            Button(
                                modifier = Modifier.width(60.dp),
                                onClick = {
                                    openDialog.value = false
                                    authViewModel.logout(
                                        context = mContext,
                                        navController
                                    )
                                }
                            ) {
                                Text("Yes")
                            }
                        }
                    },
                    title = {
                        Text(
                            text = "Logout",
                            fontFamily = AppFonts.poppins,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    text = {
                        Text(
                            text = "Are you sure want to logout ?",
                            fontFamily = AppFonts.poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                )
            }

            // SECTION MOTIVATE AND SEARCHING
            SearchBookSection(navController = navController)

            // BANNER ROW SCROLL
            BannerSection()

            // SECTION RECENT READ
            RecentlyReadingSection(navController = navController, booksData = booksFromFirestore)

            // SECTION READING LIST
            ReadingListSection(navController = navController, booksData = booksFromFirestore)

            // SECTION TRENDING BOOK
        }
    }
}


@Composable
fun ReadingListSection(
    modifier: Modifier = Modifier,
    navController: NavController,
    booksData: List<BookModel>
) {
    Spacer(modifier = modifier.height(24.dp))
    Text(
        text = "Reading List",
        fontFamily = AppFonts.poppins,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        overflow = TextOverflow.Ellipsis,
    )
    Spacer(modifier = modifier.height(2.dp))
    LazyRow {
        items(booksData) { book ->
            CardBookItem(
                bookItem = book,
                onPressDetail = { navController.navigate(ReaderAppScreens.DetailBookScreen.name) })

        }
    }

}

@Composable
fun RecentlyReadingSection(
    modifier: Modifier = Modifier,
    navController: NavController,
    booksData: List<BookModel>
) {
    Spacer(modifier = modifier.height(24.dp))
    Text(
        text = "Recently Reading",
        fontFamily = AppFonts.poppins,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        overflow = TextOverflow.Ellipsis,
    )
    Spacer(modifier = modifier.height(2.dp))
    LazyRow {
        items(booksData) { book ->
            CardBookItem(
                bookItem = book,
                onPressDetail = { navController.navigate(ReaderAppScreens.DetailBookScreen.name) })

        }
    }
}


@Composable
fun BannerSection(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(24.dp))
    Row(modifier.horizontalScroll(rememberScrollState())) {
        CardBanner()
        CardBanner()
        CardBanner()
    }
}


@Composable
fun SearchBookSection(
    modifier: Modifier = Modifier,
    navController: NavController,
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
            .background(color = Color.White)
            .clickable {
                navController.navigate(ReaderAppScreens.SearchScreen.name)
            },
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "search",
                modifier = modifier.padding(end = 8.dp),
                tint = Color.Gray
            )
            Text(
                text = "Search book...",
                fontFamily = AppFonts.poppins,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
            )

        }
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
//            HomeLayout()
        }
    }
}