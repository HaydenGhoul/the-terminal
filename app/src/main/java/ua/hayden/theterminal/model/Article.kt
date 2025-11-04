package ua.hayden.theterminal.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Article(
    @StringRes val headline: Int,
    @StringRes val subheadline: Int,
    @StringRes val author: Int,
    @StringRes val newsOrg: Int,
    @DrawableRes val image: Int,
    @StringRes val imageDescription: Int,
    @StringRes val text: Int
)
