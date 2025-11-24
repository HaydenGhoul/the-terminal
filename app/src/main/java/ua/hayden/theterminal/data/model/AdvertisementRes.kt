package ua.hayden.theterminal.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import ua.hayden.theterminal.domain.model.NewsFeed.Advertisement
import ua.hayden.theterminal.utils.ResourceProvider

@Immutable
data class AdvertisementRes(
    val id: Int,
    @DrawableRes val image: Int,
    @StringRes val headline: Int,
    @StringRes val caption: Int,
    @StringRes val model: Int,
    @StringRes val url: Int?,
) {
    fun toAdvertisement(resources: ResourceProvider) = Advertisement(
        id = id,
        image = image,
        headline = resources.getString(headline),
        caption = resources.getString(caption),
        model = resources.getString(model),
        url = url?.let { resources.getString(url) }
    )
}
