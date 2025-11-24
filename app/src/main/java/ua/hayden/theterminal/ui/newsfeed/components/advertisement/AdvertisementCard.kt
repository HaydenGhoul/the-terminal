package ua.hayden.theterminal.ui.newsfeed.components.advertisement

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.hayden.theterminal.data.repository.NewsFeedPreviewData.getAdvertisementForPrev
import ua.hayden.theterminal.domain.model.NewsFeed.Advertisement
import ua.hayden.theterminal.ui.theme.AppDimens
import ua.hayden.theterminal.ui.theme.TheTerminalTheme

/**
 * Displays an advertisement card with image, text and model info.
 * The entire card is clickable and triggers the provided URL callback.
 *
 * @param modifier Applied to the card layout.
 * @param advertisement The [Advertisement] data displayed inside the card.
 * @param onCardClick Lambda invoked with the ad URL when the card is clicked.
 */
@Composable
fun AdvertisementCard(
    modifier: Modifier = Modifier,
    advertisement: Advertisement,
    onCardClick: (String?) -> Unit
) {
    Card(
        onClick = { onCardClick(advertisement.url) },
        border = BorderStroke(1.dp, LocalContentColor.current),
        modifier = modifier.fillMaxWidth()
    ) {
        AdvertisementCardContent(advertisement = advertisement)
    }
}

/**
 * Renders the internal layout of an advertisement card, including image,
 * headline, caption and model block.
 *
 * @param modifier Modifier applied to the container.
 * @param advertisement The [Advertisement] data providing the image and passing the data in other
 *        sections.
 */
@Composable
fun AdvertisementCardContent(modifier: Modifier = Modifier, advertisement: Advertisement) {
    Column(modifier = modifier) {
        Image(
            painter = painterResource(advertisement.image),
            colorFilter = ColorFilter.tint(LocalContentColor.current),
            contentDescription = null
        )
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(AppDimens.PaddingMedium)
        ) {
            AdvertisementBody(Modifier.weight(1f), advertisement)
            AdvertisementModel(advertisement = advertisement)
        }
    }
}

@Preview(showBackground = false)
@Composable
fun AdvertisementCardLightPreview() {
    val context = LocalContext.current
    TheTerminalTheme {
        Surface {
            AdvertisementCard(
                advertisement = getAdvertisementForPrev(context),
                onCardClick = {}
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun AdvertisementCardDarkPreview() {
    val context = LocalContext.current
    TheTerminalTheme(darkTheme = true) {
        Surface {
            AdvertisementCard(
                advertisement = getAdvertisementForPrev(context),
                onCardClick = {}
            )
        }
    }
}