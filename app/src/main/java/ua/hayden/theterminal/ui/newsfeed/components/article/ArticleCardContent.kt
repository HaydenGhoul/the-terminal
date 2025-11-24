package ua.hayden.theterminal.ui.newsfeed.components.article

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.OpenInNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import ua.hayden.theterminal.R
import ua.hayden.theterminal.data.repository.NewsFeedPreviewData.getArticleForPrev
import ua.hayden.theterminal.domain.model.NewsFeed.Article
import ua.hayden.theterminal.ui.theme.AppDimens
import ua.hayden.theterminal.ui.theme.TheTerminalTheme

/**
 * Displays the full content of an [Article] within a card, supporting expandable behavior.
 *
 * The layout adapts to the card's expansion state:
 * - When collapsed, only a portion of the article (headlines, byline, and limited body)
 *   is visible, with a fade overlay to indicate more content.
 * - When expanded, all content is displayed with animated resizing.
 *
 * @param modifier Modifier applied to the container.
 * @param article The [Article] data displayed inside the content layout.
 * @param isExpanded Controls whether the card shows its full content.
 * @param onReadMoreClick Lambda invoked when the "Read More" button is clicked, passing
 *  *     the article string URL.
 *
 */
@Composable
fun ArticleCardContent(
    modifier: Modifier = Modifier,
    article: Article,
    isExpanded: Boolean,
    onReadMoreClick: (String?) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppDimens.SpacerSizeMedium),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = if (isExpanded) Dp.Unspecified else AppDimens.CollapsedHeight)
                .padding(
                    start = AppDimens.PaddingMedium,
                    top = AppDimens.PaddingMedium,
                    end = AppDimens.PaddingMedium
                )
        ) {
            ArticleHeadlines(Modifier.fillMaxWidth(), article)
            ArticleByline(Modifier.fillMaxWidth(), article)
            ArticleBody(Modifier.fillMaxWidth(), article, isExpanded)
            ReadMoreButton(
                Modifier.padding(bottom = AppDimens.PaddingMedium),
                isExpanded,
            ) { onReadMoreClick(article.url) }
        }
        ArticleFadeOverlay(
            Modifier.align(Alignment.BottomCenter),
            !isExpanded
        )
    }
}

/**
 * Displays a fade overlay at the bottom of an article card to indicate that more content
 * is available when the card is collapsed.
 *
 * The overlay uses a vertical gradient from transparent to the card background color.
 * It is only visible when [isCollapsed] is true.
 *
 * @param modifier Modifier applied to the overlay
 * @param isCollapsed Determines whether the overlay should be shown.
 */
@Composable
fun ArticleFadeOverlay(modifier: Modifier = Modifier, isCollapsed: Boolean) {
    if (isCollapsed) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(AppDimens.FadeOverlayHeight)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            CardDefaults.cardColors().containerColor
                        )
                    )
                )
        )
    }
}

/**
 * Displays a "Read More" button with an icon, used to reveal the full article content.
 *
 * The button is only shown when [isExpanded] is true.
 *
 * @param modifier Modifier applied to the button
 * @param onClick Lambda invoked when the user clicks the button.
 * @param isExpanded Controls whether the button is visible.
 */
@Composable
fun ReadMoreButton(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    if (isExpanded) {
        Button(
            onClick = onClick,
            modifier = modifier
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.OpenInNew,
                contentDescription = null,
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(stringResource(R.string.label_read_more))
        }
    }
}

@Preview
@Composable
fun ArticleCardContentLightPreview() {
    val context = LocalContext.current
    TheTerminalTheme {
        Surface(color = CardDefaults.cardColors().containerColor) {
            ArticleCardContent(
                isExpanded = true,
                article = getArticleForPrev(context, 0),
                onReadMoreClick = { }
            )
        }
    }
}

@Preview
@Composable
fun ArticleCardContentDarkPreview() {
    val context = LocalContext.current
    TheTerminalTheme(darkTheme = true) {
        Surface(color = CardDefaults.cardColors().containerColor) {
            ArticleCardContent(
                isExpanded = true,
                article = getArticleForPrev(context, 1),
                onReadMoreClick = {}
            )
        }
    }
}