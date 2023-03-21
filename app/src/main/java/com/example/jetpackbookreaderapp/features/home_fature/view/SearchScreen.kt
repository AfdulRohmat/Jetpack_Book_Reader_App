package com.example.jetpackbookreaderapp.features.search_feature.view

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.Item
import com.example.jetpackbookreaderapp.features.home_fature.view_model.HomeViewModel
import com.example.jetpackbookreaderapp.global_components.CustomTopAppBar
import com.example.jetpackbookreaderapp.navigations.ReaderAppScreens
import com.example.jetpackbookreaderapp.utils.AppColors
import com.example.jetpackbookreaderapp.utils.AppFonts

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchBookScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val search = rememberSaveable() { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(search.value) {
        search.value.trim().isNotEmpty()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CustomTopAppBar(title = "Search Book", onNavigationIcon = {
                navController.popBackStack()
            })
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // SEARCH BOOK SECTION
            SearchBookSection(
                modifier = Modifier.padding(bottom = 16.dp),
                searchState = search,
                onAction = KeyboardActions {
                    if (!valid) return@KeyboardActions
                    // doing search functionality
                    homeViewModel.getSearchBook(search.value)

                    // Log.d("search", search.value)
                    keyboardController?.hide()
                    search.value = ""
                })

            // SEARCH RESULT SECTION
            SearchResultSection(homeViewModel = homeViewModel, navController = navController)
        }
    }
}

@Composable
fun SearchResultSection(homeViewModel: HomeViewModel, navController: NavController) {
    if (homeViewModel.isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(color = AppColors.mBlue)
        }
    }

    // total results
    if (homeViewModel.searchBookResult.isNotEmpty()) {
        Text(
            text = "Results : ${homeViewModel.searchBookResult.size.toString()}",
            fontFamily = AppFonts.poppins,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black.copy(alpha = 0.8F),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }

    LazyColumn(modifier = Modifier.fillMaxSize(), content = {
        items(items = homeViewModel.searchBookResult) { item ->
            SearchResultCard(searchBookModelItem = item, onPressDetail = {
                navController.navigate(ReaderAppScreens.DetailBookScreen.name + "/${item.id}")
            })
        }
    })

}

@Composable
fun SearchResultCard(
    modifier: Modifier = Modifier,
    searchBookModelItem: Item,
    onPressDetail: () -> Unit
) {
    Card(
        elevation = 4.dp,
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(vertical = 6.dp)
            .clickable { onPressDetail() },
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            // COVER BOOK
            Image(
                painter = rememberImagePainter(data = searchBookModelItem.volumeInfo.imageLinks.thumbnail), // using coil library to handle image from network
                contentDescription = "book_cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(140.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(2.dp)
            )

            // COLUM DESCRIPTION
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .padding(start = 8.dp)
                    .fillMaxSize()
            ) {
                // title
                Text(
                    text = searchBookModelItem.volumeInfo.title.toString(),
                    fontFamily = AppFonts.poppins,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black.copy(alpha = 0.8F),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                // author
                Text(
                    text =  "${searchBookModelItem.volumeInfo.authors ?: "No Data"}",
                    modifier = modifier
                        .fillMaxWidth(),
                    fontFamily = AppFonts.poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(alpha = 0.8F),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                // publisher
                Text(
                    text = searchBookModelItem.volumeInfo.publisher ?: "No Data",
                    modifier = modifier
                        .fillMaxWidth(),
                    fontFamily = AppFonts.poppins,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(alpha = 0.8F),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                // year
                Text(
                    text = "Published Date : ${searchBookModelItem.volumeInfo.publishedDate.toString()}",
                    modifier = modifier
                        .fillMaxWidth(),
                    fontFamily = AppFonts.poppins,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(alpha = 0.8F),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                // rating
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "star",
                        modifier = modifier
                            .padding(end = 8.dp)
                            .size(20.dp),
                        tint = AppColors.mYellow
                    )
                    Text(
                        text = "${searchBookModelItem.volumeInfo.averageRating ?: "No Data"}",
                        fontFamily = AppFonts.poppins,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray,
                        overflow = TextOverflow.Ellipsis,
                    )
                }


            }


        }
    }
}

@Composable
fun SearchBookSection(
    modifier: Modifier = Modifier,
    searchState: MutableState<String>,
    imeAction: ImeAction = ImeAction.Search,
    keyboardType: KeyboardType = KeyboardType.Text,
    onAction: KeyboardActions = KeyboardActions.Default,
) {
    // SEARCH BOOK
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
            textStyle = TextStyle(fontSize = 16.sp, color = Color.Gray),
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val search = rememberSaveable() { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
//            SearchBookLayout(searchState = search)
//            SearchResultCard(onPressDetail = {})
        }
    }
}