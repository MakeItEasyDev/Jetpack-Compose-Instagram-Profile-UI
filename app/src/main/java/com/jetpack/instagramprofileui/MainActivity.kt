package com.jetpack.instagramprofileui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.instagramprofileui.ui.theme.InstagramProfileUITheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramProfileUITheme {
                Surface(color = MaterialTheme.colors.background) {
                    InstagramProfileUI()
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun InstagramProfileUI() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "makeiteasydev",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            actions = {
                Icon(
                    Icons.Outlined.Notifications,
                    contentDescription = "Notification",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    Icons.Outlined.MoreVert,
                    contentDescription = "More option",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(10.dp))
            },
            navigationIcon = {
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    Icons.Outlined.ArrowBack,
                    contentDescription = "Back option",
                    tint = Color.Black
                )
            },
            backgroundColor = Color.White,
            elevation = 0.dp
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                RoundImage(
                    image = painterResource(id = R.drawable.makeiteasy),
                    modifier = Modifier
                        .size(100.dp)
                        .weight(3f)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.weight(7f)
                ) {
                    ProfileStat(numberText = "143", text = "Posts")
                    ProfileStat(numberText = "182", text = "Followers")
                    ProfileStat(numberText = "142", text = "Following")
                }
            }
            ProfileDescription(
                displayName = "MakeItEasyDev",
                hashTag = "#AndroidDev #JetpackCompose",
                description = "I have post in Jetpack Compose related videos and mini project...",
                url = "https://www.youtube.com/channel/UCF5zBo6bPwMySOSZJXEhL4A",
                followedBy = listOf("dhanasekar.mech", "anand_krishna111")
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            ActionButton(
                text = "Following",
                icon = Icons.Default.KeyboardArrowDown,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.45f)
                    .height(30.dp)
            )
            ActionButton(
                text = "Message",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.45f)
                    .height(30.dp)
            )
            ActionButton(
                icon = Icons.Default.KeyboardArrowDown,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f)
                    .height(30.dp)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        PostTabView(
            imageModels = listOf(
                ImageModel(
                    image = painterResource(id = R.drawable.ic_grid),
                    text = "Posts"
                ),
                ImageModel(
                    image = painterResource(id = R.drawable.ic_outline_person_pin_24),
                    text = "Person"
                )
            )
        ) {
            selectedTabIndex = it
        }
        when(selectedTabIndex) {
            0 -> PostSection(
                posts = listOf(
                    painterResource(id = R.drawable.banner1),
                    painterResource(id = R.drawable.banner2),
                    painterResource(id = R.drawable.banner3),
                    painterResource(id = R.drawable.banner4),
                    painterResource(id = R.drawable.banner5),
                    painterResource(id = R.drawable.banner6),
                    painterResource(id = R.drawable.banner7),
                    painterResource(id = R.drawable.banner8),
                    painterResource(id = R.drawable.banner9)
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = "Images",
        modifier = modifier
            .aspectRatio(
                1f, matchHeightConstraintsFirst = true
            )
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}

@Composable
fun ProfileStat(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text)
    }
}

@Composable
fun ProfileDescription(
    displayName: String,
    hashTag: String,
    description: String,
    url: String,
    followedBy: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            lineHeight = 20.sp
        )
        Text(
            text = hashTag,
            color = Color(0xFF3D3D91),
            lineHeight = 20.sp
        )
        Text(
            text = description,
            lineHeight = 20.sp
        )
        Text(
            text = url,
            color = Color(0xFF3D3D91),
            lineHeight = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (followedBy.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    append("Followed by")
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()
                        if (index < followedBy.size - 1) {
                            append(", ")
                        }
                    }
                },
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(6.dp)
    ) {
        if (text != null) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
        if (icon != null) {
            Icon(
                icon,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    imageModels: List<ImageModel>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier
    ) {
        imageModels.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color(0xFF777777)
            ) {
                Icon(
                    painter = item.image,
                    contentDescription = "Item Image",
                    tint = if (selectedTabIndex == index) Color.Black else Color.LightGray,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun PostSection(
    posts: List<Painter>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        modifier = modifier.scale(1.01f)
    ) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = "Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}



















