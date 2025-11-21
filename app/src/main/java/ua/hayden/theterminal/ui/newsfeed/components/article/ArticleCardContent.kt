package ua.hayden.theterminal.ui.newsfeed.components.article

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.model.ResourceProviderImpl
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
 * @param isCardExpanded Controls whether the card shows its full content.
 * @param onReadMoreClick Lambda invoked when the "Read More" button is clicked, passing
 *  *     the article string URL.
 *
 */
@Composable
fun ArticleCardContent(
    modifier: Modifier = Modifier,
    article: Article,
    isCardExpanded: Boolean,
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
                .heightIn(max = if (isCardExpanded) Dp.Unspecified else AppDimens.CollapsedHeight)
                .padding(
                    start = AppDimens.PaddingMedium,
                    top = AppDimens.PaddingMedium,
                    end = AppDimens.PaddingMedium
                )
        ) {
            ArticleHeadlines(Modifier.fillMaxWidth(), article)
            ArticleByline(Modifier.fillMaxWidth(), article)
            ArticleBody(Modifier.fillMaxWidth(), article, isCardExpanded)
            ReadMoreButton(
                Modifier.padding(bottom = AppDimens.PaddingMedium),
                { onReadMoreClick(article.url) },
                isCardExpanded
            )
        }
        ArticleFadeOverlay(
            Modifier.align(Alignment.BottomCenter),
            !isCardExpanded
        )
    }
}

/**
 * Preview of [ArticleCardContent] in light theme.
 */
@Preview
@Composable
fun ArticleCardContentLightPreview() {
    val context = LocalContext.current
    val article = NewsFeedRepository.firstArticleExample.toUi(ResourceProviderImpl(context))
    TheTerminalTheme {
        Surface(color = CardDefaults.cardColors().containerColor) {
            ArticleCardContent(
                isCardExpanded = true,
                article = article,
                onReadMoreClick = { }
            )
        }
    }
}

/**
 * Preview of [ArticleCardContent] in dark theme.
 */
@Preview
@Composable
fun ArticleCardContentDarkPreview() {
    val context = LocalContext.current
    val article = NewsFeedRepository.secondArticleExample.toUi(ResourceProviderImpl(context))
    TheTerminalTheme(darkTheme = true) {
        Surface(color = CardDefaults.cardColors().containerColor) {
            ArticleCardContent(
                isCardExpanded = true,
                article = article,
                onReadMoreClick = {}
            )
        }
    }
}