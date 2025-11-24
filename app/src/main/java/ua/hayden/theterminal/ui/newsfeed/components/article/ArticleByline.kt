package ua.hayden.theterminal.ui.newsfeed.components.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import ua.hayden.theterminal.R
import ua.hayden.theterminal.domain.model.NewsFeed.Article
import ua.hayden.theterminal.ui.theme.article

/**
 * Displays the byline section of an [Article], including
 * author and news organization with styled text.
 *
 * @param modifier Modifier applied to the container.
 * @param article The [Article] providing author and news organization data.
 */
@Composable
fun ArticleByline(
    modifier: Modifier = Modifier,
    article: Article,
) {
    Column(
        modifier = modifier
    ) {
        BylineAuthor(Modifier.fillMaxWidth(), article.author)
        BylineNewsOrg(Modifier.fillMaxWidth(), article.newsOrg)
    }
}

@Composable
fun BylineAuthor(modifier: Modifier = Modifier, author: String) {
    Text(
        text = stringResource(R.string.label_by) + " " + author,
        style = MaterialTheme.typography.article.byline,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
fun BylineNewsOrg(modifier: Modifier = Modifier, newsOrg: String) {
    val text = buildAnnotatedString {
        withStyle(MaterialTheme.typography.article.newsOrg.toSpanStyle()) {
            append(stringResource(R.string.label_staff_reporter_of))
            append(" ")
        }
        withStyle(MaterialTheme.typography.article.newsOrgSC.toSpanStyle()) {
            append(newsOrg)
        }
    }
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}