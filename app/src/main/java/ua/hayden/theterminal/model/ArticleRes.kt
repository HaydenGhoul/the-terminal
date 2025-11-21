package ua.hayden.theterminal.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

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
    fun toUi(res: ResourceProvider) = Article(
        id = id,
        headline = res.getString(headline),
        subheadline = res.getString(subheadline),
        author = res.getString(author),
        newsOrg = res.getString(newsOrg),
        image = image,
        imageCaption = res.getString(imageCaption),
        text = res.getString(text),
        url = url?.let { res.getString(url) }
    )
}
