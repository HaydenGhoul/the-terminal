package ua.hayden.theterminal.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

sealed interface NewsFeed {
    val id: Int

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

    @Immutable
    data class Advertisement(
        override val id: Int,
        @DrawableRes val image: Int,
        val headline: String,
        val caption: String,
        val model: String,
        val url: String?
    ) : NewsFeed
}