package ua.hayden.theterminal.ui.newsfeed

import android.content.Intent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import ua.hayden.theterminal.model.NewsFeed
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.model.ResourceProviderImpl
import ua.hayden.theterminal.ui.newsfeed.components.article.ArticleCardContent
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.viewmodel.NewsFeedViewModel
import ua.hayden.theterminal.viewmodel.UiEvent

/**
 * High-level entry point for rendering the news feed screen.
 *
 * Delegates all layout and item rendering to [NewsFeedGrid], passing through
 * window size information, padding, grid state, and view-model-driven actions.
 *
 * @param modifier Modifier applied to the [NewsFeedGrid] layout.
 * @param widthSizeClass Determines layout adaptations based on window size.
 * @param contentPadding Padding provided by the Scaffold layout.
 * @param gridState Scroll state for the staggered grid.
 * @param viewModel Provides [NewsFeed] data and event flow.
 */
@Composable
fun NewsFeedScreen(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    contentPadding: PaddingValues,
    gridState: LazyStaggeredGridState,
    viewModel: NewsFeedViewModel
) {
    NewsFeedGrid(
        modifier = modifier,
        widthSizeClass = widthSizeClass,
        contentPadding = contentPadding,
        gridState = gridState,
        viewModel = viewModel,
        onReadMoreClick = viewModel::openUrlOrSnackbar,
        onAdvertisementCardClick = viewModel::openUrlOrSnackbar
    )
}

/**
 * Preview of [NewsFeedScreen] in light theme.
 */
@Preview(showBackground = false)
@Composable
fun NewsFeedScreenLightPreview() {
    val context = LocalContext.current

    val viewModel = remember {
        NewsFeedViewModel(
            repository = NewsFeedRepository,
            resProvider = ResourceProviderImpl(context)
        )
    }
    TheTerminalTheme {
        NewsFeedScreen(
            widthSizeClass = WindowWidthSizeClass.Compact,
            contentPadding = PaddingValues(0.dp),
            gridState = rememberLazyStaggeredGridState(),
            viewModel = viewModel
        )
    }
}

/**
 * Preview of [NewsFeedScreen] in dark theme.
 */
@Preview(showBackground = false)
@Composable
fun NewsFeedScreenDarkPreview() {
    val context = LocalContext.current

    val viewModel = remember {
        NewsFeedViewModel(
            repository = NewsFeedRepository,
            resProvider = ResourceProviderImpl(context)
        )
    }
    TheTerminalTheme(darkTheme = true) {
        NewsFeedScreen(
            widthSizeClass = WindowWidthSizeClass.Compact,
            contentPadding = PaddingValues(0.dp),
            gridState = rememberLazyStaggeredGridState(),
            viewModel = viewModel
        )
    }
}