@file:OptIn(ExperimentalMaterial3Api::class)

package ua.hayden.theterminal.ui.app

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import ua.hayden.theterminal.R
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.model.ResourceProviderImpl
import ua.hayden.theterminal.ui.newsfeed.NewsFeedScreen
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.viewmodel.NewsFeedViewModel
import ua.hayden.theterminal.viewmodel.UiEvent
import ua.hayden.theterminal.model.NewsFeed
import ua.hayden.theterminal.ui.newsfeed.NewsFeedGrid

/**
 * The root composable of The Terminal app.
 *
 * Handles app layout including top bar, floating action button, snackbar host, and
 * news feed screen. Also collects and reacts to events from [NewsFeedViewModel] such as
 * opening URLs or showing snackbars.
 *
 * @param modifier Modifier applied to the [NewsFeedScreen] layout.
 * @param widthSizeClass Determines layout adaptations based on window size.
 * @param viewModel Provides [NewsFeed] data and event flow.
 */
@Composable
fun TheTerminalApp(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    viewModel: NewsFeedViewModel
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val gridState = rememberLazyStaggeredGridState()

    val snackbarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                is UiEvent.OpenUrl -> {
                    val intent = Intent(Intent.ACTION_VIEW, event.url.toUri())
                    context.startActivity(intent)
                }

                is UiEvent.ShowSnackbar -> {
                    scope.launch {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = { TheTerminalTopAppBar(scrollBehavior = scrollBehavior) },
        floatingActionButton = { ScrollToTopButton(gridState = gridState) },
        snackbarHost = { SnackbarHost(snackbarHostState) { TheTerminalSnackbar(it) } },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        NewsFeedScreen(
            modifier = modifier,
            widthSizeClass = widthSizeClass,
            contentPadding = innerPadding,
            gridState = gridState,
            viewModel = viewModel
        )
    }
}

/**
 * Custom [Snackbar] that matches The Terminal app theme.
 *
 * @param data The [SnackbarData] to display.
 */
@Composable
fun TheTerminalSnackbar(data: SnackbarData) {
    Snackbar(
        snackbarData = data,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        actionColor = MaterialTheme.colorScheme.primary
    )
}

/**
 * Displays the top app bar for The Terminal app.
 *
 * Shows the app name and edition label centered within a [CenterAlignedTopAppBar].
 * Supports scroll behavior to collapse/expand on scroll.
 *
 * @param modifier Modifier applied to the [CenterAlignedTopAppBar] layout.
 * @param scrollBehavior Controls top app bar scroll interactions.
 */
@Composable
fun TheTerminalTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    CenterAlignedTopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.app_name_uppercase),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = stringResource(R.string.edition_online),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

/**
 * Floating action button that scrolls the [LazyStaggeredGridState] to the top when pressed.
 * Becomes visible only when the user scrolls up and the list is not at the top.
 *
 * @param modifier Modifier applied to the [FloatingActionButton] layout.
 * @param gridState The [LazyStaggeredGridState] to control scrolling.
 */
@Composable
fun ScrollToTopButton(
    modifier: Modifier = Modifier,
    gridState: LazyStaggeredGridState
) {
    val scope = rememberCoroutineScope()
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(gridState) {
        var previous = gridState.firstVisibleItemIndex to gridState.firstVisibleItemScrollOffset
        snapshotFlow { gridState.firstVisibleItemIndex to gridState.firstVisibleItemScrollOffset }
            .distinctUntilChanged()
            .collect { (currentIndex, currentOffset) ->
                val (previousIndex, previousOffset) = previous
                val scrolledUp = when {
                    currentIndex < previousIndex -> true
                    currentIndex == previousIndex && currentOffset < previousOffset -> true
                    else -> false
                }
                isVisible = scrolledUp && (currentIndex > 0 || currentOffset > 0)
                previous = currentIndex to currentOffset
            }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { it * 2 }) +
                fadeIn(
                    initialAlpha = 0.0f,
                    animationSpec = tween(durationMillis = 150)
                ),
        exit = slideOutVertically(
            targetOffsetY = { it * 2 }) +
                fadeOut(
                    targetAlpha = 0.0f,
                    animationSpec = tween(durationMillis = 150)
                )
    ) {
        FloatingActionButton(
            onClick = { scope.launch { gridState.animateScrollToItem(0) } },
            content = { Icon(Icons.Rounded.KeyboardArrowUp, contentDescription = null) },
            modifier = modifier
        )
    }
}

/**
 * Preview of [TheTerminalApp] in light theme.
 */
@Preview(showBackground = false)
@Composable
fun TheTerminalAppLightPreview() {
    val context = LocalContext.current

    val viewModel = remember {
        NewsFeedViewModel(
            repository = NewsFeedRepository,
            resProvider = ResourceProviderImpl(context)
        )
    }
    TheTerminalTheme {
        TheTerminalApp(
            widthSizeClass = WindowWidthSizeClass.Compact,
            viewModel = viewModel
        )
    }
}

/**
 * Preview of [TheTerminalApp] in dark theme.
 */
@Preview(showBackground = false)
@Composable
fun TheTerminalAppDarkPreview() {
    val context = LocalContext.current

    val viewModel = remember {
        NewsFeedViewModel(
            repository = NewsFeedRepository,
            resProvider = ResourceProviderImpl(context)
        )
    }
    TheTerminalTheme(darkTheme = true) {
        TheTerminalApp(
            widthSizeClass = WindowWidthSizeClass.Compact,
            viewModel = viewModel
        )
    }
}