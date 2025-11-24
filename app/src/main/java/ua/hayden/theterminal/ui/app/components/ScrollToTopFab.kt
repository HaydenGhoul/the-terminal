package ua.hayden.theterminal.ui.app.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

/**
 * Floating action button that scrolls the [LazyStaggeredGridState] to the top when pressed.
 * Becomes visible only when the user scrolls up and the list is not at the top.
 *
 * @param modifier Modifier applied to the [FloatingActionButton] layout.
 * @param staggeredGridState The [LazyStaggeredGridState] to control scrolling.
 */
@Composable
fun ScrollToTopFab(
    modifier: Modifier = Modifier,
    staggeredGridState: LazyStaggeredGridState
) {
    val scope = rememberCoroutineScope()
    var isFabVisible by remember { mutableStateOf(false) }

    LaunchedEffect(staggeredGridState) {
        var previousScrollPosition = staggeredGridState.firstVisibleItemIndex to
                staggeredGridState.firstVisibleItemScrollOffset
        snapshotFlow { staggeredGridState.firstVisibleItemIndex to
                staggeredGridState.firstVisibleItemScrollOffset }
            .distinctUntilChanged()
            .collect { currentScrollPosition ->
                val (previousIndex, previousOffset) = previousScrollPosition
                val (currentIndex, currentOffset) = currentScrollPosition
                val scrolledUp = when {
                    currentIndex < previousIndex -> true
                    currentIndex == previousIndex && currentOffset < previousOffset -> true
                    else -> false
                }
                isFabVisible = scrolledUp && (currentIndex > 0 || currentOffset > 0)
                previousScrollPosition = currentScrollPosition
            }
    }

    AnimatedVisibility(
        visible = isFabVisible,
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
            onClick = { scope.launch { staggeredGridState.animateScrollToItem(0) } },
            content = { Icon(Icons.Rounded.KeyboardArrowUp, null) },
            modifier = modifier
        )
    }
}