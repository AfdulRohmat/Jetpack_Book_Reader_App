package com.example.jetpackbookreaderapp.features.search_feature.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.jetpackbookreaderapp.global_components.CustomTopAppBar
import com.example.jetpackbookreaderapp.utils.AppColors
import com.example.jetpackbookreaderapp.utils.AppFonts

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchBookScreen(navController: NavController) {
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
                    // ...


                    Log.d("search", search.value)
                    search.value = ""
                    keyboardController?.hide()
                })

            // SEARCH RESULT SECTION
            SearchResultSection()
        }
    }
}

@Composable
fun SearchResultSection() {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 8.dp)
    ) {
        SearchResultCard(onPressDetail = {})
        SearchResultCard(onPressDetail = {})
        SearchResultCard(onPressDetail = {})
        SearchResultCard(onPressDetail = {})
        SearchResultCard(onPressDetail = {})
        SearchResultCard(onPressDetail = {})
        SearchResultCard(onPressDetail = {})
        SearchResultCard(onPressDetail = {})
        SearchResultCard(onPressDetail = {})
        SearchResultCard(onPressDetail = {})
        SearchResultCard(onPressDetail = {})
        SearchResultCard(onPressDetail = {})

    }

}

@Composable
fun SearchResultCard(modifier: Modifier = Modifier, onPressDetail: () -> Unit) {
    Card(
        elevation = 4.dp,
        modifier = modifier
            .height(160.dp)
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
                painter = rememberImagePainter(data = "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1461170085i/29966226._SX50_.jpg"), // using coil library to handle image from network
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
                // name
                Text(
                    text = "Harry Potter",
                    fontFamily = AppFonts.poppins,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black.copy(alpha = 0.8F),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                // author
                Text(
                    text = "Jk. Rowling",
                    modifier = modifier
                        .fillMaxWidth(),
                    fontFamily = AppFonts.poppins,
                    fontSize = 14.sp,
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
                        text = "4.8",
                        fontFamily = AppFonts.poppins,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                // edition
                Text(
                    text = "Edition : 6",
                    modifier = modifier
                        .fillMaxWidth(),
                    fontFamily = AppFonts.poppins,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(alpha = 0.8F),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

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
            SearchResultCard(onPressDetail = {})
        }
    }
}