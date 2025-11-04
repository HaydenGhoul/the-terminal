package ua.hayden.theterminal.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import ua.hayden.theterminal.R
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.ArticleRepository
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.ui.theme.bodyArticle
import ua.hayden.theterminal.ui.theme.headlineArticle
import ua.hayden.theterminal.ui.theme.labelArticleAuthor
import ua.hayden.theterminal.ui.theme.labelImageDescription
import ua.hayden.theterminal.ui.theme.labelNewsOrg
import ua.hayden.theterminal.ui.theme.labelNewsOrgSC
import ua.hayden.theterminal.ui.theme.subheadlineArticle

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    widthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact
) {
    var expanded by remember { mutableStateOf(false) }

    Card(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Text(
                text = stringResource(article.headline),
                style = MaterialTheme.typography.headlineArticle,
            )
            HorizontalDivider(
                color = LocalContentColor.current,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(vertical = dimensionResource(R.dimen.padding_medium))
            )
            Text(
                text = stringResource(article.subheadline),
                style = MaterialTheme.typography.subheadlineArticle
            )
            HorizontalDivider(
                color = LocalContentColor.current,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(vertical = dimensionResource(R.dimen.padding_medium))
            )

            when (widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    if (expanded) {
                        ArticleContent(
                            article = article
                        )
                    }
                    ExpandButton(
                        expanded = expanded,
                        onClick = {
                            expanded = !expanded
                        }
                    )
                }

                else -> {
                    ArticleContent(
                        article = article
                    )
                }
            }
        }
    }
}

@Composable
fun ArticleContent(
    modifier: Modifier = Modifier,
    article: Article
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(MaterialTheme.typography.labelArticleAuthor.toSpanStyle()) {
                    append(stringResource(R.string.article_author_by))
                    append(" ")
                    append(stringResource(article.author))
                }
            },
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = buildAnnotatedString {
                withStyle(MaterialTheme.typography.labelNewsOrg.toSpanStyle()) {
                    append(stringResource(R.string.label_staff_reporter_of))
                    append(" ")
                }
                withStyle(MaterialTheme.typography.labelNewsOrgSC.toSpanStyle()) {
                    append(stringResource(article.newsOrg))
                }
            },
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        ArticleImage(
            article = article
        )
        Text(
            text = stringResource(article.text),
            style = MaterialTheme.typography.bodyArticle
        )
    }
}

@Composable
fun ArticleImage(
    modifier: Modifier = Modifier,
    article: Article
) {
    val image = painterResource(article.image)
    val isPortrait = image.intrinsicSize.height > image.intrinsicSize.width
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(LocalContentColor.current),
            modifier = Modifier
                .fillMaxWidth(if (isPortrait) 0.75f else 1f)
                .padding(vertical = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(article.imageDescription),
            style = MaterialTheme.typography.labelImageDescription
        )
        HorizontalDivider(
            color = LocalContentColor.current,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_medium)
                )
        )
    }
}

@Composable
fun ExpandButton(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.padding(
            top = if (expanded) dimensionResource(R.dimen.padding_medium)
            else dimensionResource(R.dimen.padding_zero)
        )
    ) {
        val icon =
            if (expanded) R.drawable.round_expand_less_24 else R.drawable.round_expand_more_24
        val label = if (expanded) R.string.label_read_less else R.string.label_read_more
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(
            text = stringResource(label)
        )
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ArticleAdaptiveGrid(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(R.dimen.padding_zero)),
    articles: List<Article>
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(
            when (widthSizeClass) {
                WindowWidthSizeClass.Compact -> 1
                WindowWidthSizeClass.Medium -> 2
                WindowWidthSizeClass.Expanded -> 3
                else -> 1
            }
        ),
        verticalItemSpacing = dimensionResource(R.dimen.vertical_item_spacing),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.horizontal_item_spacing)),
        contentPadding = contentPadding,
        modifier = modifier.padding(horizontal = dimensionResource(R.dimen.padding_small))
    ) {
        items(articles) { article ->
            ArticleCard(
                article = article,
                widthSizeClass = widthSizeClass,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ArticleCardLightPreview() {
    TheTerminalTheme {
        ArticleCard(
            article = ArticleRepository.firstArticleExample
        )
    }
}

@Preview(showBackground = false)
@Composable
fun ArticleCardDarkPreview() {
    TheTerminalTheme(darkTheme = true) {
        ArticleCard(
            article = ArticleRepository.secondArticleExample
        )
    }
}

@Preview(showBackground = false)
@Composable
fun ArticleListAdaptiveLightPreview() {
    TheTerminalTheme {
        ArticleAdaptiveGrid(
            articles = ArticleRepository.articleList
        )
    }
}

@Preview(showBackground = false)
@Composable
fun ArticleListAdaptiveDarkPreview() {
    TheTerminalTheme(darkTheme = true) {
        ArticleAdaptiveGrid(
            articles = ArticleRepository.articleList
        )
    }
}