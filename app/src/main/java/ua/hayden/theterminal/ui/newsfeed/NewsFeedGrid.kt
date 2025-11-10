package ua.hayden.theterminal.ui.newsfeed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.hayden.theterminal.R
import ua.hayden.theterminal.model.Advertisement
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.ui.theme.TheTerminalTheme

/**
 * Displays a [LazyVerticalStaggeredGrid] containing [ArticleCard] items and an [AdvertisementCard] item.
 *
 * @param modifier For styling and layout.
 * @param widthSizeClass Determines how many columns are displayed in [LazyVerticalStaggeredGrid].
 * @param contentPadding Padding around grid content.
 * @param gridState
 * @param articles The [List] with [Article] data displayed inside each card.
 * @param advertisement The [Advertisement] object displays in the end of the grid
 */
@Composable
fun NewsFeedGrid(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    contentPadding: PaddingValues,
    gridState: LazyStaggeredGridState,
    articles: List<Article>,
    advertisement: Advertisement
) {
    val columnCount = when (widthSizeClass) {
        WindowWidthSizeClass.Compact -> 1
        WindowWidthSizeClass.Medium -> 2
        WindowWidthSizeClass.Expanded -> 3
        else -> 1
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(columnCount),
        state = gridState,
        verticalItemSpacing = dimensionResource(R.dimen.vertical_item_spacing),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.horizontal_item_spacing)),
        contentPadding = contentPadding,
        modifier = modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
    ) {
        items(articles) { article ->
            ArticleCard(
                article = article,
                widthSizeClass = widthSizeClass,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item { AdvertisementCard(advertisement = advertisement) }
    }
}

/**
 * Preview of [NewsFeedGrid] in light theme.
 */
@Preview(showBackground = false)
@Composable
fun NewsFeedGridLightPreview() {
    TheTerminalTheme {
        NewsFeedGrid(
            widthSizeClass = WindowWidthSizeClass.Compact,
            contentPadding = PaddingValues(0.dp),
            gridState = rememberLazyStaggeredGridState(),
            articles = NewsFeedRepository.articleList,
            advertisement = NewsFeedRepository.rigaAdvertisement
        )
    }
}

/**
 * Preview of [NewsFeedGrid] in dark theme.
 */
@Preview(showBackground = false)
@Composable
fun NewsFeedGridDarkPreview() {
    TheTerminalTheme(darkTheme = true) {
        NewsFeedGrid(
            widthSizeClass = WindowWidthSizeClass.Compact,
            contentPadding = PaddingValues(0.dp),
            gridState = rememberLazyStaggeredGridState(),
            articles = NewsFeedRepository.articleList,
            advertisement = NewsFeedRepository.rigaAdvertisement
        )
    }
}