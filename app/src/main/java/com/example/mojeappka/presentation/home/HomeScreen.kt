package com.example.mojeappka.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.mojeappka.R
import com.example.mojeappka.domain.model.Article
import com.example.mojeappka.presentation.Dimension.ExtraSmallPadding
import com.example.mojeappka.presentation.Dimension.MediumPadding1
import com.example.mojeappka.presentation.Dimension.SmallPadding
import com.example.mojeappka.presentation.common.ArticlesList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = ExtraSmallPadding)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = SmallPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_splash),
                contentDescription = null,
                modifier = Modifier
                    .width(110.dp)
                    .height(70.dp)
                    .padding(horizontal = SmallPadding)
                    .clip(CircleShape)
            )
            Text(
                text = "BBC News",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = SmallPadding, start = MediumPadding1),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(MediumPadding1))

        Text(
            text = "Mějte ty nejnovější informace vždy po ruce. Ať už se jedná o sport, vědu, vzdělání, politiku, umění, krásu i zábavu.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticlesList(modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = {
                navigateToDetails(it)
            })
    }
}