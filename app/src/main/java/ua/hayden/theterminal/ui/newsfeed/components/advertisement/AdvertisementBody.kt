package ua.hayden.theterminal.ui.newsfeed.components.advertisement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.hayden.theterminal.domain.model.NewsFeed.Advertisement
import ua.hayden.theterminal.ui.theme.AppDimens
import ua.hayden.theterminal.ui.theme.advertisement

/**
 * Displays the textual body of an advertisement: headline and caption.
 *
 * @param modifier Modifier applied to the container.
 * @param advertisement The [Advertisement] data providing the text for headline and caption.
 *
 */
@Composable
fun AdvertisementBody(modifier: Modifier = Modifier, advertisement: Advertisement) {
    Column(
        verticalArrangement = Arrangement.spacedBy(AppDimens.SpacerSizeMedium),
        modifier = modifier
    ) {
        Text(
            text = advertisement.headline,
            style = MaterialTheme.typography.advertisement.headline
        )
        Text(
            text = advertisement.caption,
            style = MaterialTheme.typography.advertisement.caption
        )
    }
}