package com.example.mojeappka.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.mojeappka.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Title 01",
        description = "Description 01 blahblahblahblahblahblahblahblahblahblahblahblahblahblahblah",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Title 02",
        description = "Description 02 blahblahblahblahblahblahblahblahblahblahblahblahblahblahblah",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Title 03",
        description = "Description 03 blahblahblahblahblahblahblahblahblahblahblahblahblahblahblah",
        image = R.drawable.onboarding3
    )
)