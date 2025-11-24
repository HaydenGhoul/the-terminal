package ua.hayden.theterminal.ui.newsfeed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.hayden.theterminal.data.repository.NewsFeedPreviewData.getNewsFeedItemsForPreview
import ua.hayden.theterminal.domain.model.NewsFeed
import ua.hayden.theterminal.domain.model.NewsFeed.Advertisement
import ua.hayden.theterminal.domain.model.NewsFeed.Article
import ua.hayden.theterminal.ui.newsfeed.components.advertisement.AdvertisementCard
import ua.hayden.theterminal.ui.newsfeed.components.article.ArticleCard
import ua.hayden.theterminal.ui.theme.AppDimens
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.utils.toGridColumnCount

/**
 * Displays the news feed as a [LazyVerticalStaggeredGrid], mixing [ArticleCard] entries
 * with [AdvertisementCard] items.
 *
 * Layout behavior:
 * - Number of columns is determined by [widthSizeClass].
 * - In compact layouts, article cards can expand or collapse individually.
 * - In larger layouts, article cards are always fully expanded.
 *
 * @param modifier Layout modifier for the grid.
 * @param staggeredGridState Scroll state for the staggered grid.
 * @param widthSizeClass Determines the number of grid columns.
 * @param contentPadding Padding around the grid content.
 * @param gridItems List of [NewsFeed] items to display.
 * @param expandedArticleCards Map of article IDs to their expanded/collapsed state.
 * @param onArticleClick Callback for article card clicks.
 * @param onReadMoreClick Callback invoked when "Read More" is pressed in article card.
 * @param onAdClick Callback for advertisement card clicks.
 */
@Composable
fun NewsFeedGrid(
    modifier: Modifier = Modifier,
    staggeredGridState: LazyStaggeredGridState,
    widthSizeClass: WindowWidthSizeClass,
    contentPadding: PaddingValues,
    gridItems: List<NewsFeed>,
    expandedArticleCards: Map<Int, Boolean>,
    onArticleClick: (Int) -> Unit,
    onReadMoreClick: (String?) -> Unit,
    onAdClick: (String?) -> Unit
) {
    val isWindowCompact = widthSizeClass == WindowWidthSizeClass.Compact

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(widthSizeClass.toGridColumnCount()),
        state = staggeredGridState,
        verticalItemSpacing = AppDimens.VerticalItemSpacing,
        horizontalArrangement = Arrangement.spacedBy(AppDimens.HorizontalItemSpacing),
        contentPadding = PaddingValues(
            top = contentPadding.calculateTopPadding() + AppDimens.PaddingSmall,
            bottom = contentPadding.calculateBottomPadding() + AppDimens.PaddingSmall
        ),
        modifier = modifier.padding(horizontal = AppDimens.PaddingMedium)
    ) {
        items(gridItems, key = { it.id }) { newsFeedItem ->
            when (newsFeedItem) {
                is Article -> {
                    val cardExpandedState = expandedArticleCards[newsFeedItem.id] ?: false
                    val isCardExpanded = if (isWindowCompact) cardExpandedState else true
                    ArticleCard(
                        article = newsFeedItem,
                        isWindowCompact = isWindowCompact,
                        isCardExpanded = isCardExpanded,
                        onCardClick = { onArticleClick(newsFeedItem.id) },
                        onReadMoreClick = onReadMoreClick
                    )
                }

                is Advertisement -> {
                    AdvertisementCard(
                        advertisement = newsFeedItem,
                        onCardClick = onAdClick
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun NewsFeedGridLightPreview() {
    val context = LocalContext.current
    val (previewItems, expandedCards) = getNewsFeedItemsForPreview(context, expandArticles = false)

    TheTerminalTheme {
        NewsFeedGrid(
            widthSizeClass = WindowWidthSizeClass.Compact,
            contentPadding = PaddingValues(0.dp),
            staggeredGridState = rememberLazyStaggeredGridState(),
            gridItems = previewItems,
            expandedArticleCards = expandedCards,
            onArticleClick = {},
            onReadMoreClick = {},
            onAdClick = {}
        )
    }
}

@Preview(showBackground = false)
@Composable
fun NewsFeedGridDarkPreview() {
    val context = LocalContext.current
    val (previewItems, expandedCards) = getNewsFeedItemsForPreview(context, expandArticles = false)

    TheTerminalTheme(darkTheme = true) {
        NewsFeedGrid(
            widthSizeClass = WindowWidthSizeClass.Compact,
            contentPadding = PaddingValues(0.dp),
            staggeredGridState = rememberLazyStaggeredGridState(),
            gridItems = previewItems,
            expandedArticleCards = expandedCards,
            onArticleClick = {},
            onReadMoreClick = {},
            onAdClick = {}
        )
    }
}