package ua.hayden.theterminal.ui.newsfeed.components.article

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Compact
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ua.hayden.theterminal.data.repository.NewsFeedPreviewData.getArticleForPrev
import ua.hayden.theterminal.domain.model.NewsFeed.Article
import ua.hayden.theterminal.ui.newsfeed.NewsFeedGrid
import ua.hayden.theterminal.ui.theme.TheTerminalTheme

/**
 * Displays a single article card within the [NewsFeedGrid], adapting its content based
 * on window size and expansion state.
 *
 *
 * @param modifier Modifier applied to the [ArticleCardContent] container.
 * @param isWindowCompact Determines if the current [WindowWidthSizeClass] is [Compact].
 * @param article The [Article] data displayed inside the card.
 * @param isCardExpanded Controls whether the card shows the full article content.
 * @param onCardClick Lambda invoked when the card itself is clicked.
 * @param onReadMoreClick Lambda invoked when the “Read More” button is clicked, passing
 *        an optional String URL for the full article.
 */
@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    isWindowCompact: Boolean,
    article: Article,
    isCardExpanded: Boolean,
    onCardClick: () -> Unit,
    onReadMoreClick: (String?) -> Unit
) {
    AdaptiveCard(Modifier.fillMaxWidth(), isWindowCompact, onCardClick) {
        ArticleCardContent(modifier, article, isCardExpanded, onReadMoreClick)
    }
}

@Composable
fun AdaptiveCard(
    modifier: Modifier = Modifier,
    isClickable: Boolean,
    onClick: () -> Unit,
    content: @Composable (ColumnScope) -> Unit
) {
    if (isClickable) {
        Card(modifier = modifier, onClick = onClick, content = content)
    } else {
        Card(modifier = modifier, content = content)
    }
}



@Preview(showBackground = false)
@Composable
fun ArticleCardLightPreview() {
    val context = LocalContext.current
    TheTerminalTheme {
        ArticleCard(
            article = getArticleForPrev(context, 0),
            isWindowCompact = true,
            isCardExpanded = false,
            onCardClick = {},
            onReadMoreClick = {}
        )
    }
}

@Preview(showBackground = false)
@Composable
fun ArticleCardDarkPreview() {
    val context = LocalContext.current
    TheTerminalTheme(darkTheme = true) {
        ArticleCard(
            article = getArticleForPrev(context, 1),
            isWindowCompact = true,
            isCardExpanded = false,
            onCardClick = {},
            onReadMoreClick = {}
        )
    }
}