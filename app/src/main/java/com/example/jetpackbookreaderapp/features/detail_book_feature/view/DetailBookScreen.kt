package com.example.jetpackbookreaderapp.features.detail_book_feature.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.jetpackbookreaderapp.data.Resource
import com.example.jetpackbookreaderapp.features.detail_book_feature.view_model.DetailBookViewModel
import com.example.jetpackbookreaderapp.features.home_fature.model.detail_book_model.DetailBookModel
import com.example.jetpackbookreaderapp.global_components.CustomTopAppBar
import com.example.jetpackbookreaderapp.utils.AppColors
import com.example.jetpackbookreaderapp.utils.AppFonts

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "ProduceStateDoesNotAssignValue")
@Composable
fun DetailBookScreen(
    navController: NavController,
    bookId: String,
    detailBookViewModel: DetailBookViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CustomTopAppBar(title = "Search Book", onNavigationIcon = {
                navController.popBackStack()
            })
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = AppColors.mBlue,
                elevation = 2.dp,
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = {}) {
                        Text(
                            text = "Add to Reading List",
                            fontFamily = AppFonts.poppins,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            // CALL DETAIL BOOK VIEW MODEL
            val detailBookResult = produceState<Resource<DetailBookModel>>(
                initialValue = Resource.Loading(),
                producer = {
                    value = detailBookViewModel.getDetailBook(bookId)
                }).value

            if (detailBookResult.data == null) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(color = AppColors.mBlue)
                }
            } else {
                Text(text = "book id : $bookId")

                // IMAGE BOOK SECTION
                ImageBookSection(modifier = Modifier, detailBookData = detailBookResult)

                // BUTTON GET MORE INFO
                GetMoreInfoSection(
                    modifier = Modifier,
                    detailBookData = detailBookResult,
                    onGetMoreInfo = {})

                // DETAIL SECTION
                DetailSection(modifier = Modifier, detailBookData = detailBookResult)

                // SYNOPSIS SECTION
                SynopsisSection(modifier = Modifier, detailBookData = detailBookResult)
            }
        }
    }
}

@Composable
fun GetMoreInfoSection(
    modifier: Modifier,
    onGetMoreInfo: () -> Unit,
    detailBookData: Resource<DetailBookModel>
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextButton(onClick = { onGetMoreInfo() }) {
            Text(
                text = "Get More Information..",
                fontFamily = AppFonts.poppins,
                fontSize = 13.sp,
                color = AppColors.mBlue,
                fontWeight = FontWeight.Normal,
            )
        }

    }
}

@Composable
fun SynopsisSection(modifier: Modifier, detailBookData: Resource<DetailBookModel>) {
    Column(
        modifier = Modifier.padding(top = 14.dp, bottom = 80.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = detailBookData.data?.synopsis.toString(),
            fontFamily = AppFonts.poppins,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black.copy(alpha = 0.8F),
            maxLines = 100,
            overflow = TextOverflow.Ellipsis,
        )
    }


}

@Composable
fun DetailSection(modifier: Modifier, detailBookData: Resource<DetailBookModel>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        // Rating
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
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
                text = detailBookData.data?.rating.toString(),
                fontFamily = AppFonts.poppins,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
            )
        }

        // title
        Text(
            text = detailBookData.data?.name.toString(),
            fontFamily = AppFonts.poppins,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            maxLines = 2,
        )

        // author
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
        ) {
            detailBookData.data?.authors?.size?.let {
                items(it) {
                    detailBookData.data.authors.forEach { author ->
                        Text(
                            text = author,
                            fontFamily = AppFonts.poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black.copy(alpha = 0.8F),
                        )
                    }
                }
            }
        }

        // row other desc
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            // published date
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                // year
                Text(
                    text = detailBookData.data?.published_date.toString(),
                    fontFamily = AppFonts.poppins,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    color = Color.Black.copy(alpha = 0.8F),
                    maxLines = 2,
                )
                // desc
                Text(
                    text = "Published In",
                    fontFamily = AppFonts.poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(alpha = 0.8F),
                    textAlign = TextAlign.Center
                )
            }

            // total pages
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                // year
                Text(
                    text = detailBookData.data?.pages.toString(),
                    fontFamily = AppFonts.poppins,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black.copy(alpha = 0.8F),
                    maxLines = 1,
                )
                // desc
                Text(
                    text = "Pages",
                    fontFamily = AppFonts.poppins,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(alpha = 0.8F),
                    textAlign = TextAlign.Center
                )
            }


        }
    }

}

@Composable
fun ImageBookSection(modifier: Modifier, detailBookData: Resource<DetailBookModel>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    ) {
        Card(modifier = modifier.padding(vertical = 18.dp), elevation = 4.dp) {
            Image(
                painter = rememberImagePainter(data = detailBookData.data?.cover), // using coil library to handle image from network
                contentDescription = "book_cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(180.dp)
                    .background(color = AppColors.mBlue)
            )
        }


    }

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CustomTopAppBar(title = "Search Book", onNavigationIcon = {
            })
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = AppColors.mBlue,
                elevation = 2.dp,
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = {}) {
                        Text(
                            text = "Add to Reading List",
                            fontFamily = AppFonts.poppins,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }

                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
//            ImageBookSection(modifier = Modifier, detailBookData = detailBookResult)
//            GetMoreInfoSection(
//                modifier = Modifier,
//                onGetMoreInfo = {},
//                detailBookData = detailBookResult
//            )
//            DetailSection(modifier = Modifier, detailBookData = detailBookResult)
//            SynopsisSection(modifier = Modifier, detailBookData = detailBookResult)
        }
    }
}