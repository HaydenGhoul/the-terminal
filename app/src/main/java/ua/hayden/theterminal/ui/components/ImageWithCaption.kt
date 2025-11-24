package ua.hayden.theterminal.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import ua.hayden.theterminal.ui.theme.AppDimens
import ua.hayden.theterminal.ui.theme.article

/**
 * Displays an article image with a caption below it.
 *
 * - The image width can be adjusted using [imageWidthFraction] relative to the parent width.
 * - The caption is displayed below the image using [TextWithDivider], with an optional
 *   adaptive divider that adjusts based on text length.
 *
 * @param modifier Modifier applied to the container Column.
 * @param painter The [Painter] used to render the image.
 * @param caption The text displayed below the image.
 * @param imageWidthFraction Fraction of the parent width to occupy (default 1f = full width).
 * @param verticalSpacing Vertical spacing between the image and caption
 *        (default defined by AppDimens.SpacerSizeMedium = 16.dp).
 */
@Composable
fun ImageWithCaption(
    modifier: Modifier = Modifier,
    painter: Painter,
    caption: String,
    imageWidthFraction: Float = 1f,
    verticalSpacing: Dp = AppDimens.SpacerSizeMedium
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(LocalContentColor.current),
            modifier = Modifier.fillMaxWidth(imageWidthFraction)
        )
        TextWithDivider(
            text = caption,
            style = MaterialTheme.typography.article.imageCaption,
            isAdaptiveDivider = true
        )
    }
}