@file:OptIn(ExperimentalMaterial3Api::class)

package ua.hayden.theterminal.ui.app

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import kotlinx.coroutines.launch
import ua.hayden.theterminal.data.repository.NewsFeedRepository
import ua.hayden.theterminal.domain.model.NewsFeed
import ua.hayden.theterminal.ui.app.components.ScrollToTopFab
import ua.hayden.theterminal.ui.app.components.TerminalSnackbar
import ua.hayden.theterminal.ui.app.components.TerminalTopAppBar
import ua.hayden.theterminal.ui.newsfeed.NewsFeedScreen
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.utils.ResourceProviderImpl
import ua.hayden.theterminal.viewmodel.NewsFeedUiEvent
import ua.hayden.theterminal.viewmodel.NewsFeedViewModel

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
    val staggeredGridState = rememberLazyStaggeredGridState()
    val snackbarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(viewModel) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is NewsFeedUiEvent.OpenUrl -> {
                    val intent = Intent(Intent.ACTION_VIEW, event.url.toUri())
                    context.startActivity(intent)
                }

                is NewsFeedUiEvent.ShowSnackbar -> {
                    coroutineScope.launch {
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
        topBar = { TerminalTopAppBar(scrollBehavior = scrollBehavior) },
        floatingActionButton = { ScrollToTopFab(staggeredGridState = staggeredGridState) },
        snackbarHost = { SnackbarHost(snackbarHostState) { TerminalSnackbar(it) } },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        NewsFeedScreen(
            modifier = modifier,
            widthSizeClass = widthSizeClass,
            contentPadding = innerPadding,
            staggeredGridState = staggeredGridState,
            viewModel = viewModel
        )
    }
}

@Preview(showBackground = false)
@Composable
fun TheTerminalAppLightPreview() {
    val context = LocalContext.current

    val viewModel = remember {
        NewsFeedViewModel(
            repository = NewsFeedRepository,
            resourceProvider = ResourceProviderImpl(context)
        )
    }
    TheTerminalTheme {
        TheTerminalApp(
            widthSizeClass = WindowWidthSizeClass.Compact,
            viewModel = viewModel
        )
    }
}

@Preview(showBackground = false)
@Composable
fun TheTerminalAppDarkPreview() {
    val context = LocalContext.current

    val viewModel = remember {
        NewsFeedViewModel(
            repository = NewsFeedRepository,
            resourceProvider = ResourceProviderImpl(context)
        )
    }
    TheTerminalTheme(darkTheme = true) {
        TheTerminalApp(
            widthSizeClass = WindowWidthSizeClass.Compact,
            viewModel = viewModel
        )
    }
}