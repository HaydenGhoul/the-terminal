package ua.hayden.theterminal.ui.newsfeed.components.article

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.model.ResourceProviderImpl
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.ui.newsfeed.NewsFeedGrid
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Compact

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

/**
 * Displays a card that can optionally be clickable, adapting behavior based on [clickEnabled].
 *
 * If [clickEnabled] is true, the card is interactive and invokes [onClick] when tapped.
 * Otherwise, the card is displayed as static content.
 *
 * @param modifier Modifier applied to the card layout.
 * @param clickEnabled Determines whether the card should respond to clicks.
 * @param onClick Lambda invoked when the card is clicked (only used if [clickEnabled] is true).
 * @param content The composable content displayed inside the card, with [ColumnScope] as
 *  *             receiver to allow vertical arrangement of elements.
 */
@Composable
fun AdaptiveCard(
    modifier: Modifier = Modifier,
    clickEnabled: Boolean,
    onClick: () -> Unit,
    content: @Composable (ColumnScope) -> Unit
) {
    if (clickEnabled) {
        Card(modifier = modifier, onClick = onClick, content = content)
    } else {
        Card(modifier = modifier, content = content)
    }
}

/**
 * Preview of [ArticleCard] in light theme.
 */
@Preview(showBackground = false)
@Composable
fun ArticleCardLightPreview() {
    val context = LocalContext.current
    val article = NewsFeedRepository.firstArticleExample.toUi(ResourceProviderImpl(context))
    TheTerminalTheme {
        ArticleCard(
            article = article,
            isWindowCompact = true,
            isCardExpanded = false,
            onCardClick = {},
            onReadMoreClick = {}
        )
    }
}

/**
 * Preview of [ArticleCard] in dark theme.
 */
@Preview(showBackground = false)
@Composable
fun ArticleCardDarkPreview() {
    val context = LocalContext.current
    val article = NewsFeedRepository.secondArticleExample.toUi(ResourceProviderImpl(context))
    TheTerminalTheme(darkTheme = true) {
        ArticleCard(
            article = article,
            isWindowCompact = true,
            isCardExpanded = false,
            onCardClick = {},
            onReadMoreClick = {}
        )
    }
}