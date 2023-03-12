package com.example.jetpackbookreaderapp.features.home_fature.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackbookreaderapp.R
import com.example.jetpackbookreaderapp.utils.AppColors
import com.example.jetpackbookreaderapp.utils.AppFonts


@Composable
fun CardBookItem(modifier: Modifier = Modifier, onTap: () -> Unit) {
    Column(
        modifier = modifier
            .width(180.dp)
            .padding(horizontal = 8.dp)
            .clickable { onTap() },
    ) {
        Card(
            elevation = 4.dp,
            modifier = modifier
                .height(240.dp)
                .width(180.dp)
                .background(color = Color.White),
            shape = RoundedCornerShape(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                // Image book
                Image(
                    painter = painterResource(id = R.drawable.book_cover),
                    contentDescription = "book_cover",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()

                )

                // status
                Text(
                    text = "Reading",
                    modifier = modifier
                        .fillMaxWidth()
                        .background(color = AppColors.mBlue)
                        .padding(4.dp),
                    fontFamily = AppFonts.poppins,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Spacer(modifier = modifier.height(4.dp))

        // Row title and love
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Text(
                text = "Harry Potter",
                fontFamily = AppFonts.poppins,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black.copy(alpha = 0.8F),
                overflow = TextOverflow.Ellipsis,
            )
            IconButton(
                onClick = {

                }, modifier = Modifier
                    .height(25.dp)
                    .width(25.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "favorite",
                    tint = Color.Red.copy(alpha = 0.9F)
                )
            }
        }

        // row rating
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "star",
                modifier = modifier.padding(end = 8.dp),
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


        // author
        Text(
            text = "Jk. Rowling",
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp),
            fontFamily = AppFonts.poppins,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black.copy(alpha = 0.8F),
            overflow = TextOverflow.Ellipsis,
        )
    }

}


@Composable
fun CardBanner(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
    ) {
        Surface(
            elevation = 4.dp,
            modifier = modifier
                .height(180.dp)
                .fillMaxWidth()
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
}