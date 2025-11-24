package ua.hayden.theterminal.ui.newsfeed.components.advertisement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ua.hayden.theterminal.domain.model.NewsFeed.Advertisement
import ua.hayden.theterminal.ui.theme.advertisement

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