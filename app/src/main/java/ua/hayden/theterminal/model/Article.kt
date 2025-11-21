package ua.hayden.theterminal.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class Article(
    override val id: Int,
    val headline: String,
    val subheadline: String,
    val author: String,
    val newsOrg: String,
    @DrawableRes val image: Int,
    val imageCaption: String,
    val text: String,
    val url: String?
) : NewsFeed
