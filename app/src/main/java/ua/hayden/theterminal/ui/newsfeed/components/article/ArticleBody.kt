package ua.hayden.theterminal.ui.newsfeed.components.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import eu.wewox.textflow.material3.TextFlow
import eu.wewox.textflow.material3.TextFlowObstacleAlignment
import ua.hayden.theterminal.data.repository.NewsFeedPreviewData.getArticleForPrev
import ua.hayden.theterminal.domain.model.NewsFeed.Article
import ua.hayden.theterminal.ui.components.ImageWithCaption
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
 *   @param isExpanded Controls whether images are shown and how they are laid out.
 */
@Composable
fun ArticleBody(
    modifier: Modifier = Modifier,
    article: Article,
    isExpanded: Boolean
) {
    val painter = painterResource(article.image)
    val isImagePortrait = painter.intrinsicSize.height > painter.intrinsicSize.width

    val showPortraitImage = isExpanded && isImagePortrait
    val showLandscapeImage = isExpanded && !isImagePortrait

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

@Preview
@Composable
fun ArticleBodyLightPreview() {
    val context = LocalContext.current
    TheTerminalTheme {
        Surface {
            ArticleBody(
                article = getArticleForPrev(context, 0),
                isExpanded = true
            )
        }
    }
}

@Preview
@Composable
fun ArticleBodyDarkPreview() {
    val context = LocalContext.current
    TheTerminalTheme(darkTheme = true) {
        Surface {
            ArticleBody(
                article = getArticleForPrev(context, 1),
                isExpanded = true
            )
        }
    }
}