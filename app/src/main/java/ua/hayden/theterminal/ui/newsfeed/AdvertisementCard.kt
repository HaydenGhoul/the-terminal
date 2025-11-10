package ua.hayden.theterminal.ui.newsfeed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.hayden.theterminal.R
import ua.hayden.theterminal.model.Advertisement
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.ui.theme.advertisement

/**
 * @param modifier
 * @param advertisement
 */
@Composable
fun AdvertisementCard(
    modifier: Modifier = Modifier,
    advertisement: Advertisement,
) {
    Card(
        onClick = {},
        border = BorderStroke(1.dp, LocalContentColor.current),
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Image(
                painter = painterResource(advertisement.image),
                colorFilter = ColorFilter.tint(LocalContentColor.current),
                contentDescription = null
            )
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(advertisement.headline),
                        style = MaterialTheme.typography.advertisement.headline
                    )
                    Spacer(Modifier.size(dimensionResource(R.dimen.padding_medium)))
                    Text(
                        text = stringResource(advertisement.caption),
                        style = MaterialTheme.typography.advertisement.caption
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .height(IntrinsicSize.Max)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            val text = stringResource(advertisement.model)
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
        }
    }
}

/**
 * Preview of [AdvertisementCard] in light theme.
 */
@Preview(showBackground = false)
@Composable
fun AdvertisementCardLightPreview() {
    TheTerminalTheme {
        Surface {
            AdvertisementCard(
                advertisement = NewsFeedRepository.rigaAdvertisement
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
    TheTerminalTheme(darkTheme = true) {
        Surface {
            AdvertisementCard(
                advertisement = NewsFeedRepository.rigaAdvertisement
            )
        }
    }
}