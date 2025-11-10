@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)

package ua.hayden.theterminal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import ua.hayden.theterminal.ui.newsfeed.NewsFeedScreen
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.viewmodel.NewsFeedViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheTerminalTheme {
                val windowSizeClass = calculateWindowSizeClass(this@MainActivity)
                val widthSizeClass = windowSizeClass.widthSizeClass
                val viewModel: NewsFeedViewModel = viewModel()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TheTerminalApp(widthSizeClass, viewModel)
                }
            }
        }
    }
}

/**
 * The root composable of The Terminal app.
 *
 * @param widthSizeClass Used to
 * @param viewModel The [NewsFeedViewModel] that provides the article lis state.
 */
@Composable
fun TheTerminalApp(
    widthSizeClass: WindowWidthSizeClass,
    viewModel: NewsFeedViewModel
) {
    val gridState = rememberLazyStaggeredGridState()
    val articles by viewModel.articles
    val advertisement by viewModel.advertisement

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = { TheTerminalTopAppBar(scrollBehavior = scrollBehavior) },
        floatingActionButton = { ScrollToTopButton(gridState = gridState) },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        NewsFeedScreen(
            widthSizeClass = widthSizeClass,
            contentPadding = innerPadding,
            gridState = gridState,
            articles = articles,
            advertisement = advertisement
        )
    }
}

/**
 * Displays the top app bar for The Terminal app.
 *
 * Shows the app title and edition label centered within a [CenterAlignedTopAppBar].
 *
 * @param modifier For styling and layout.
 * @param scrollBehavior
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
 * @param modifier
 * @param gridState
 */
@Composable
fun ScrollToTopButton(
    modifier: Modifier = Modifier,
    gridState: LazyStaggeredGridState
) {
    val scope = rememberCoroutineScope()
    var isVisible by remember { mutableStateOf(false) }
    var previousIndex by remember { mutableIntStateOf(gridState.firstVisibleItemIndex) }

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.firstVisibleItemIndex }
            .collect { currentIndex ->
                when {
                    currentIndex < previousIndex && currentIndex > 0 -> { isVisible = true }
                    currentIndex > previousIndex || currentIndex == 0 -> { isVisible = false }
                }
                previousIndex = currentIndex
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
            modifier = modifier.graphicsLayer(clip = false)
        )
    }
}

/**
 * Preview of [TheTerminalApp] in light theme.
 */
@Preview(showBackground = false)
@Composable
fun TheTerminalAppLightPreview() {
    TheTerminalTheme {
        TheTerminalApp(
            widthSizeClass = WindowWidthSizeClass.Compact,
            viewModel = viewModel()
        )
    }
}

/**
 * Preview of [TheTerminalApp] in dark theme.
 */
@Preview(showBackground = false)
@Composable
fun TheTerminalAppDarkPreview() {
    TheTerminalTheme(darkTheme = true) {
        TheTerminalApp(
            widthSizeClass = WindowWidthSizeClass.Compact,
            viewModel = viewModel()
        )
    }
}