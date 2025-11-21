package ua.hayden.theterminal.ui.newsfeed.components.article

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.ui.theme.AppDimens
import ua.hayden.theterminal.ui.theme.article

/**
 * Displays the headline and subheadline of an [Article] using [TextWithDivider].
 *
 * Each line is visually separated by a divider with a fixed spacing.
 *
 * @param modifier Modifier applied to the [TextWithDivider] container.
 * @param article The [Article] providing headline and subheadline text.
 */
@Composable
fun ArticleHeadlines(modifier: Modifier = Modifier, article: Article) {
    @Composable
    fun Headline(text: String, style: TextStyle) {
        TextWithDivider(
            modifier = modifier,
            space = AppDimens.SpacerSizeMedium,
            text = text,
            style = style,
            adaptiveDivider = false
        )
    }
    Headline(article.headline, MaterialTheme.typography.article.headline)
    Headline(article.subheadline, MaterialTheme.typography.article.subheadline)
}