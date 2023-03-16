package com.example.jetpackbookreaderapp.features.detail_book_feature.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.jetpackbookreaderapp.global_components.CustomTopAppBar
import com.example.jetpackbookreaderapp.utils.AppColors
import com.example.jetpackbookreaderapp.utils.AppFonts

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailBookScreen(navController: NavController) {
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
                            text = "Get More Information..",
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
            // IMAGE BOOK SECTION
            ImageBookSection(modifier = Modifier)

            // DETAIL SECTION
            DetailSection(modifier = Modifier)

            // SYNOPSIS SECTION
            SynopsisSection(modifier = Modifier)


        }
    }
}

@Composable
fun SynopsisSection(modifier: Modifier) {
    Column(
        modifier = Modifier.padding(top = 14.dp, bottom = 80.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "\"Alice, a novelist, meets Felix, who works in a warehouse, and asks him if he’d like to travel to Rome with her. In Dublin, her best friend, Eileen, is getting over a break-up and slips back into flirting with Simon, a man she has known since childhood. Alice, Felix, Eileen, and Simon are still young—but life is catching up with them. They desire each other, they delude each other, they get together, they break apart. They have sex, they worry about sex, they worry about their friendships and the world they live in. Are they standing in the last lighted room before the darkness, bearing witness to something? Will they find a way to believe in a beautiful world?Sally Rooney was born in 1991 and lives in Dublin, where she graduated from Trinity College. Her work has appeared in Granta, The Dublin Review, The White Review, The Stinging Fly, and the Winter Pages anthology.“Purposeful awkwardness” is how I can describe this book in just two words. I don’t know whether that goes along with supposedly being a voice of an entire generation to which I belong — but awkwardness permeates everything, and my reactions fluctuated between boredom, periodic cringing and occasional spark of recognition and relatability — which unfortunately ended up buried under the awkward bits much too often.Oh, and before I forget in the rants to come — dear writers, please oh please do not skip dialogue tags in your writing. Why??? It’s not fresh or daring but just irritating, and I know you know how to use them.It’s a story about nothing much, really. It follows four people who make up two eventual couples - Alice and Felix and on the other side of the country Eileen and Simon. There are multitudes of details of their daily routines, either pointlessly awkward or perhaps profound (depending on the point of view, I suppose) conversations - verbal and text messages - and quite a few pages of detailed, mechanistic and honestly quite boring and exceedingly awkward almost-voyeristic sex. And every other chapter there is a complete tone shift from that to the flourishing long emails between Eileen and Alice about more profound things in life, at times sophisticated and at times naive and yet painfully earnest and quite a bit reminiscent of those wine-fueled intellectual conversations in college in which truthseeking was stubbornly pursued. I can read stories about nothing much. But what made me cringe and shrug in frustrated boredom was the language of the bulk of the book, clearly done intentionally as those interlude emails show a completely different and much more engaging style. But the majority of the book is written basically like a screenplay, with step-by-step instructions. What’s with all these details? Is it an attempt at distancing the reader and have them figure things out on their own? Is it a gimmick that for some reason sounded interesting at one point? Is it compassion for the person eventually adapting this to the big or small screen? I suspect the latter, given that this will probably join the queue of Rooney adaptations. Seriously, check this out: “He asked her what she wanted to drink and then went to the bar to order. The waitress asked how he was getting on, and he answered: Good yeah, yourself? He ordered a vodka tonic and a pint of lager. Rather than carrying the bottle of tonic back to the table, he emptied it into the glass with a quick and practised movement of his wrist. The woman at the table tapped her fingers on a beermat, waiting. Her outward attitude had become more alert and lively since the man had entered the room. She looked outside now at the sunset as if it were of interest to her, though she hadn’t paid any attention to it before. When the man returned and put the drinks down, a drop of lager spilled over and she watched its rapid progress down the side of his glass.” Imagination rests here as it has nothing to do. Everything is spelled out. Everything. I mean, here’s the passage that should have been “she found her keys and opened the door”: “She walked lightly up the path and searched in her handbag for the house keys. The noise of the keys was audible somewhere inside the bag but she didn’t seem to be able to find them. He stood there not saying anything. She apologised for the delay and switched on the torch function on her phone, lighting the interior of her bag and casting a cold grey light on the front steps of the house also. He had his hands in his pockets. Got them, she said. Then she unlocked the door.” Tired reading the details yet? Oh dear, now imagine the entire book like this: “At twenty past twelve on a Wednesday afternoon, a woman sat behind a desk in a shared office in Dublin city centre, scrolling through a text document. She had very dark hair, swept back loosely into a tortoiseshell clasp, and she was wearing a grey sweater tucked into black cigarette trousers. Using the soft greasy roller on her computer mouse she skimmed over the document, eyes flicking back and forth across narrow columns of text, and occasionally she stopped, clicked, and inserted or deleted characters. Most frequently she was inserting two full stops into the name ‘WH Auden’, in order to standardise its appearance as ‘W.H. Auden’. When she reached the end of the document, she opened a search command, selected the Match Case option and searched: ‘WH’. No matches appeared.” And here is Sally Rooney for some reason explaining to Millenials how to use Google Maps: “He typed his address into the search bar without looking up. Yeah, he said. They have me on really random shifts this week. He handed her back the phone to show her the address: 16 Ocean Rise. The screen displayed a network of white streets on a background of grey, beside a blue area representing the sea.” And that is just the beginning of the book. This goes on for hundreds of pages until by the end you feel mostly desensitized — but for me that did not happen soon enough to translate into enjoyment. Why oh why would you purposefully subject your readers to pointless description and monotonous step-by-step detailing of everything? More under spoiler tag: “Squinting at the screen of her phone, she tapped the icon of a social media app. The interface opened and displayed a loading symbol.”And that loading symbol is clearly very important for this story. Of course it needs page space.",
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
fun DetailSection(modifier: Modifier) {
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
                text = "4.5",
                fontFamily = AppFonts.poppins,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
            )
        }

        // title
        Text(
            text = "Beautiful World, Where Are You",
            fontFamily = AppFonts.poppins,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            maxLines = 2,
        )

        // author
        Text(
            text = "J. K Rowling",
            fontFamily = AppFonts.poppins,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black.copy(alpha = 0.8F),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

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
                    text = "September 7, 2021",
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
                    text = "356",
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
fun ImageBookSection(modifier: Modifier) {
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
                painter = rememberImagePainter(data = ""), // using coil library to handle image from network
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
                            text = "Get More Information..",
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
            ImageBookSection(modifier = Modifier)
            DetailSection(modifier = Modifier)
            SynopsisSection(modifier = Modifier)
        }
    }
}