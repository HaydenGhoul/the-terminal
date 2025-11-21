package ua.hayden.theterminal.ui.newsfeed.components.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import eu.wewox.textflow.material3.TextFlow
import eu.wewox.textflow.material3.TextFlowObstacleAlignment
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.model.ResourceProviderImpl
import ua.hayden.theterminal.ui.theme.AppDimens
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.ui.theme.article

/**
 * Displays the main body of an [Article], including text and optional inline image.
 *
 * - If the article image is landscape and content is expanded, the image is displayed
 *   above the text with its caption.
 * - If the article image is portrait and content is expanded, the image is displayed
 *   inline with the text using [TextFlow] for text wrapping around the image.
 *
 *   @param modifier Modifier applied to the container Column.
 *   @param article The [Article] providing text and image data.
 *   @param isContentExpanded Controls whether images are shown and how they are laid out.
 */
@Composable
fun ArticleBody(
    modifier: Modifier = Modifier,
    article: Article,
    isContentExpanded: Boolean
) {
    val painter = painterResource(article.image)
    val isImagePortrait = painter.intrinsicSize.height > painter.intrinsicSize.width

    val showPortraitImage = isContentExpanded && isImagePortrait
    val showLandscapeImage = isContentExpanded && !isImagePortrait

    Column(modifier = modifier) {
        if (showLandscapeImage) {
            ImageWithCaption(
                Modifier.padding(bottom = AppDimens.PaddingMedium),
                painter = painter,
                caption = article.imageCaption
            )
        }
        TextFlow(
            text = article.text + "...",
            style = MaterialTheme.typography.article.body,
            obstacleAlignment = TextFlowObstacleAlignment.TopStart,
            modifier = Modifier.fillMaxWidth(),
            obstacleContent = {
                if (showPortraitImage) {
                    ImageWithCaption(
                        Modifier.padding(
                            end = AppDimens.PaddingSmall,
                            bottom = AppDimens.PaddingSmall),
                        painter,
                        article.imageCaption,
                        0.5f,
                    )
                }
            }
        )
    }
}

/**
 * Displays an article image with a caption below it.
 *
 * - The image width can be adjusted using [imageFraction] relative to the parent width.
 * - The caption is displayed below the image using [TextWithDivider], with an optional
 *   adaptive divider that adjusts based on text length.
 *
 * @param modifier Modifier applied to the container Column.
 * @param painter The [Painter] used to render the image.
 * @param caption The text displayed below the image.
 * @param imageFraction Fraction of the parent width to occupy (default 1f = full width).
 * @param space Vertical spacing between the image and caption
 *        (default defined by AppDimens.SpacerSizeMedium = 16.dp).
 */
@Composable
fun ImageWithCaption(
    modifier: Modifier = Modifier,
    painter: Painter,
    caption: String,
    imageFraction: Float = 1f,
    space: Dp = AppDimens.SpacerSizeMedium
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space),
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(LocalContentColor.current),
            modifier = Modifier.fillMaxWidth(imageFraction)
        )
        TextWithDivider(
            text = caption,
            style = MaterialTheme.typography.article.imageCaption,
            adaptiveDivider = true
        )
    }
}

/**
 * Preview of [ArticleBody] in light theme.
 */
@Preview
@Composable
fun ArticleBodyLightPreview() {
    val context = LocalContext.current
    val article = NewsFeedRepository.firstArticleExample.toUi(ResourceProviderImpl(context))
    TheTerminalTheme {
        Surface {
            ArticleBody(
                article = article,
                isContentExpanded = true
            )
        }
    }
}

/**
 * Preview of [ArticleBody] in dark theme.
 */
@Preview
@Composable
fun ArticleBodyDarkPreview() {
    val context = LocalContext.current
    val article = NewsFeedRepository.secondArticleExample.toUi(ResourceProviderImpl(context))
    TheTerminalTheme(darkTheme = true) {
        Surface {
            ArticleBody(
                article = article,
                isContentExpanded = true
            )
        }
    }
}