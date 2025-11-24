package ua.hayden.theterminal.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import ua.hayden.theterminal.domain.model.NewsFeed.Article
import ua.hayden.theterminal.utils.ResourceProvider

@Immutable
data class ArticleRes(
    val id: Int,
    @StringRes val headline: Int,
    @StringRes val subheadline: Int,
    @StringRes val author: Int,
    @StringRes val newsOrg: Int,
    @DrawableRes val image: Int,
    @StringRes val imageCaption: Int,
    @StringRes val text: Int,
    @StringRes val url: Int?
) {
    fun toArticle(resources: ResourceProvider) = Article(
        id = id,
        headline = resources.getString(headline),
        subheadline = resources.getString(subheadline),
        author = resources.getString(author),
        newsOrg = resources.getString(newsOrg),
        image = image,
        imageCaption = resources.getString(imageCaption),
        text = resources.getString(text),
        url = url?.let { resources.getString(url) }
    )
}
