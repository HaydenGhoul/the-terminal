package ua.hayden.theterminal.ui.newsfeed

import androidx.compose.material3.Card
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.ui.theme.TheTerminalTheme

/**
 * Displays an article card used within [NewsFeedGrid].
 *
 * The card shows an article's headline and subheadline, and depending on the [widthSizeClass] may
 * expand to display full article content with an animation. In [WindowWidthSizeClass.Compact] mode,
 * card toggles the visibility of additional article content with an expand/collapse animation.
 *
 * @param modifier For styling and layout.
 * @param widthSizeClass Determines how much content is shown depending on the display width. In
 * [WindowWidthSizeClass.Compact] mode, the card is collapsable.
 * @param article Data displayed inside the card.
 */
@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    article: Article
) {
    var isCardExpanded by remember { mutableStateOf(false) }
    val isCompact = widthSizeClass == WindowWidthSizeClass.Compact

    Card(
        onClick = { if (widthSizeClass == WindowWidthSizeClass.Compact) isCardExpanded = !isCardExpanded },
        modifier = modifier
    ) {
        ArticleContent(
            article = article,
            isWindowCompact = isCompact,
            isCardExpanded = isCardExpanded
        )
    }
}

/**
 * Preview of [ArticleCard] in light theme.
 */
@Preview(showBackground = false)
@Composable
fun ArticleCardLightPreview() {
    TheTerminalTheme {
        ArticleCard(
            widthSizeClass = WindowWidthSizeClass.Compact,
            article = NewsFeedRepository.firstArticleExample
        )
    }
}

/**
 * Preview of [ArticleCard] in dark theme.
 */
@Preview(showBackground = false)
@Composable
fun ArticleCardDarkPreview() {
    TheTerminalTheme(darkTheme = true) {
        ArticleCard(
            widthSizeClass = WindowWidthSizeClass.Compact,
            article = NewsFeedRepository.secondArticleExample
        )
    }
}