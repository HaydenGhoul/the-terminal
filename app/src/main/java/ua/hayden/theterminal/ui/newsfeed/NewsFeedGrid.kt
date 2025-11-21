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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.hayden.theterminal.model.Advertisement
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.NewsFeed
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.model.ResourceProviderImpl
import ua.hayden.theterminal.ui.newsfeed.components.advertisement.AdvertisementCard
import ua.hayden.theterminal.ui.newsfeed.components.article.ArticleCard
import ua.hayden.theterminal.ui.theme.AppDimens
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.utils.toColumnCount
import ua.hayden.theterminal.viewmodel.NewsFeedViewModel
import ua.hayden.theterminal.ui.newsfeed.components.article.ArticleCardContent

/**
 * Displays the news feed as a [LazyVerticalStaggeredGrid], mixing [ArticleCard] entries
 * with [AdvertisementCard] items.
 *
The grid adapts to window width:
 * - Column count is derived from [widthSizeClass].
 * - In compact layouts, article cards can be expanded or collapsed.
 *   In larger layouts, cards are always expanded.
 *
 * State is sourced from [NewsFeedViewModel], including the news feed items and
 * the per-card expansion map.
 *
 * @param modifier Layout modifier for the grid.
 * @param widthSizeClass Determines the number of grid columns.
 * @param contentPadding Padding around the grid content.
 * @param gridState Scroll state for the staggered grid.
 * @param viewModel Provides [NewsFeed] data and event flow.
 * @param onReadMoreClick Callback invoked when "Read More" is pressed in article card.
 * @param onAdvertisementCardClick Callback for advertisement card clicks.
 */
@Composable
fun NewsFeedGrid(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    contentPadding: PaddingValues,
    gridState: LazyStaggeredGridState,
    viewModel: NewsFeedViewModel,
    onReadMoreClick: (String?) -> Unit,
    onAdvertisementCardClick: (String?) -> Unit
) {
    val columnCount = widthSizeClass.toColumnCount()
    val isWindowCompact = widthSizeClass == WindowWidthSizeClass.Compact

    val newsFeed by viewModel.newsFeed.collectAsStateWithLifecycle()
    val expandedCards by viewModel.expandedCards.collectAsStateWithLifecycle()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(columnCount),
        state = gridState,
        verticalItemSpacing = AppDimens.VerticalItemSpacing,
        horizontalArrangement = Arrangement.spacedBy(AppDimens.HorizontalItemSpacing),
        contentPadding = PaddingValues(
            top = contentPadding.calculateTopPadding() + AppDimens.PaddingSmall,
            bottom = contentPadding.calculateBottomPadding() + AppDimens.PaddingSmall
        ),
        modifier = modifier.padding(horizontal = AppDimens.PaddingMedium)
    ) {
        items(newsFeed, key = { it.id }) { newsFeedItem ->
            when (newsFeedItem) {
                is Article -> {
                    val cardExpandedState = expandedCards[newsFeedItem.id] ?: false
                    val isCardExpanded = if (isWindowCompact) cardExpandedState else true
                    ArticleCard(
                        article = newsFeedItem,
                        isWindowCompact = isWindowCompact,
                        isCardExpanded = isCardExpanded,
                        onCardClick = { viewModel.toggleCardExpandedState(newsFeedItem.id) },
                        onReadMoreClick = onReadMoreClick
                    )
                }

                is Advertisement -> {
                    AdvertisementCard(
                        advertisement = newsFeedItem,
                        onAdvertisementCardClick = onAdvertisementCardClick
                    )
                }
            }
        }
    }
}

/**
 * Preview of [NewsFeedGrid] in light theme.
 */
@Preview(showBackground = false)
@Composable
fun NewsFeedGridLightPreview() {
    val context = LocalContext.current

    val viewModel = remember {
        NewsFeedViewModel(
            repository = NewsFeedRepository,
            resProvider = ResourceProviderImpl(context)
        )
    }
    TheTerminalTheme {
        NewsFeedGrid(
            widthSizeClass = WindowWidthSizeClass.Compact,
            contentPadding = PaddingValues(0.dp),
            gridState = rememberLazyStaggeredGridState(),
            viewModel = viewModel,
            onReadMoreClick = {},
            onAdvertisementCardClick = {}
        )
    }
}

/**
 * Preview of [NewsFeedGrid] in dark theme.
 */
@Preview(showBackground = false)
@Composable
fun NewsFeedGridDarkPreview() {
    val context = LocalContext.current

    val viewModel = remember {
        NewsFeedViewModel(
            repository = NewsFeedRepository,
            resProvider = ResourceProviderImpl(context)
        )
    }
    TheTerminalTheme(darkTheme = true) {
        NewsFeedGrid(
            widthSizeClass = WindowWidthSizeClass.Compact,
            contentPadding = PaddingValues(0.dp),
            gridState = rememberLazyStaggeredGridState(),
            viewModel = viewModel,
            onReadMoreClick = {},
            onAdvertisementCardClick = {}
        )
    }
}