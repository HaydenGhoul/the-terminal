package ua.hayden.theterminal.ui.newsfeed.components.advertisement

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.hayden.theterminal.model.Advertisement
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.model.ResourceProviderImpl
import ua.hayden.theterminal.ui.theme.AppDimens
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.ui.theme.advertisement

/**
 * Displays an advertisement card with image, text and model info.
 * The entire card is clickable and triggers the provided URL callback.
 *
 * @param modifier Applied to the card layout.
 * @param advertisement The [Advertisement] data displayed inside the card.
 * @param onAdvertisementCardClick Lambda invoked with the ad URL when the card is clicked.
 */
@Composable
fun AdvertisementCard(
    modifier: Modifier = Modifier,
    advertisement: Advertisement,
    onAdvertisementCardClick: (String?) -> Unit
) {
    Card(
        onClick = { onAdvertisementCardClick(advertisement.url) },
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

/**
 * Displays the advertisement model block, consisting of two styled text lines
 * and a thick divider used as a visual accent.
 *
 * The layout uses intrinsic sizing to ensure the divider and text align
 * tightly around the content.
 *
 * @param modifier Modifier applied to the container.
 * @param advertisement The [Advertisement] data providing the text for model.
 */
@Composable
fun AdvertisementModel(modifier: Modifier = Modifier, advertisement: Advertisement) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max)
    ) {
        Text(
            text = buildAnnotatedString {
                val text = advertisement.model
                val textParts = text.split("\n")

                withStyle(MaterialTheme.typography.advertisement.headline.toSpanStyle()) {
                    append(textParts[0].trim())
                }
                append("\n")
                withStyle(MaterialTheme.typography.advertisement.model.toSpanStyle()) {
                    append(textParts[1].trim())
                }
            },
            textAlign = TextAlign.Center
        )
        HorizontalDivider(
            color = LocalContentColor.current,
            thickness = 10.dp,
            modifier = Modifier
        )
    }
}


/**
 * Preview of [AdvertisementCard] in light theme.
 */
@Preview(showBackground = false)
@Composable
fun AdvertisementCardLightPreview() {
    val context = LocalContext.current
    val advertisement = NewsFeedRepository.advertisementExample.toUi(ResourceProviderImpl(context))
    TheTerminalTheme {
        Surface {
            AdvertisementCard(
                advertisement = advertisement,
                onAdvertisementCardClick = {}
            )
        }
    }
}

/**
 * Preview of [AdvertisementCard] in dark theme.
 */
@Preview(showBackground = false)
@Composable
fun AdvertisementCardDarkPreview() {
    val context = LocalContext.current
    val advertisement = NewsFeedRepository.advertisementExample.toUi(ResourceProviderImpl(context))
    TheTerminalTheme(darkTheme = true) {
        Surface {
            AdvertisementCard(
                advertisement = advertisement,
                onAdvertisementCardClick = {}
            )
        }
    }
}