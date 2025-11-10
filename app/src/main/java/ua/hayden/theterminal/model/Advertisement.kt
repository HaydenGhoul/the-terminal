package ua.hayden.theterminal.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Advertisement(
    @DrawableRes val image: Int = 0,
    @StringRes val headline: Int = 0,
    @StringRes val caption: Int = 0,
    @StringRes val model: Int = 0,
)
