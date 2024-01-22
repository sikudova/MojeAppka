package com.example.mojeappka.presentation.bookmark

import androidx.compose.runtime.Composable
import com.example.mojeappka.domain.model.Article
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.mojeappka.R
import com.example.mojeappka.presentation.Dimension.MediumPadding1
import com.example.mojeappka.presentation.common.ArticlesList

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigate: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
    ) {

        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                id = R.color.text_title
            )
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticlesList(
            articles = state.articles,
            onClick = navigate
        )
    }
}