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
        title = "Nejnovější BBC články",
        description = "V aplikaci se dozvíte novinky z celého světa – politika, zdraví, sport, věda, vzdělávání a mnoho dalších odvětví.",
        image = R.drawable.bbc_news
    ),
    Page(
        title = "Oblíbené články",
        description = "Při prohlížení článků si můžeš libovolný z nich uložit do složky oblíbených.",
        image = R.drawable.fav_folder
    ),
    Page(
        title = "Prohlížení článků na Internetu",
        description = "Všechny články si můžeš otevřít online ve svém prohlížeči a přečíst si je přímo na oficiálních stránkách.",
        image = R.drawable.internet_news
    )
)