package ua.hayden.theterminal.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class AdvertisementRes(
    val id: Int,
    @DrawableRes val image: Int,
    @StringRes val headline: Int,
    @StringRes val caption: Int,
    @StringRes val model: Int,
    @StringRes val url: Int?,
) {
    fun toUi(res: ResourceProvider) = Advertisement(
        id = id,
        image = image,
        headline = res.getString(headline),
        caption = res.getString(caption),
        model = res.getString(model),
        url = url?.let { res.getString(url) }
    )
}
