package ua.hayden.theterminal.ui.newsfeed

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
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
import ua.hayden.theterminal.data.repository.NewsFeedRepository
import ua.hayden.theterminal.domain.model.NewsFeed
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.utils.ResourceProviderImpl
import ua.hayden.theterminal.viewmodel.NewsFeedViewModel

/**
 * High-level entry point for rendering the news feed screen.
 *
 * Delegates all layout and item rendering to [NewsFeedGrid], passing through
 * window size information, padding, grid state, and view-model-driven actions.
 *
 * @param modifier Modifier applied to the [NewsFeedGrid] layout.
 * @param widthSizeClass Determines layout adaptations based on window size.
 * @param contentPadding Padding provided by the Scaffold layout.
 * @param staggeredGridState Scroll state for the staggered grid.
 * @param viewModel Provides [NewsFeed] data and event flow.
 */
@Composable
fun NewsFeedScreen(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    contentPadding: PaddingValues,
    staggeredGridState: LazyStaggeredGridState,
    viewModel: NewsFeedViewModel
) {
    val newsFeed by viewModel.newsFeedItems.collectAsStateWithLifecycle()
    val expandedArticleCards by viewModel.expandedArticleCards.collectAsStateWithLifecycle()

    NewsFeedGrid(
        modifier = modifier,
        staggeredGridState = staggeredGridState,
        widthSizeClass = widthSizeClass,
        contentPadding = contentPadding,
        gridItems = newsFeed,
        expandedArticleCards = expandedArticleCards,
        onArticleClick = viewModel::toggleArticleCardExpandedState,
        onReadMoreClick = viewModel::onFeedItemClick,
        onAdClick = viewModel::onFeedItemClick,
    )
}

@Preview(showBackground = false)
@Composable
fun NewsFeedScreenLightPreview() {
    val context = LocalContext.current

    val viewModel = remember {
        NewsFeedViewModel(
            repository = NewsFeedRepository,
            resourceProvider = ResourceProviderImpl(context)
        )
    }
    TheTerminalTheme {
        NewsFeedScreen(
            widthSizeClass = WindowWidthSizeClass.Compact,
            contentPadding = PaddingValues(0.dp),
            staggeredGridState = rememberLazyStaggeredGridState(),
            viewModel = viewModel
        )
    }
}

@Preview(showBackground = false)
@Composable
fun NewsFeedScreenDarkPreview() {
    val context = LocalContext.current

    val viewModel = remember {
        NewsFeedViewModel(
            repository = NewsFeedRepository,
            resourceProvider = ResourceProviderImpl(context)
        )
    }
    TheTerminalTheme(darkTheme = true) {
        NewsFeedScreen(
            widthSizeClass = WindowWidthSizeClass.Compact,
            contentPadding = PaddingValues(0.dp),
            staggeredGridState = rememberLazyStaggeredGridState(),
            viewModel = viewModel
        )
    }
}