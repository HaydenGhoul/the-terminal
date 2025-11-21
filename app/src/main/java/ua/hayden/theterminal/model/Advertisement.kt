package ua.hayden.theterminal.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class Advertisement(
    override val id: Int,
    @DrawableRes val image: Int,
    val headline: String,
    val caption: String,
    val model: String,
    val url: String?
) : NewsFeed
