package ua.hayden.theterminal.ui.newsfeed

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.hayden.theterminal.model.Advertisement
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.ui.theme.TheTerminalTheme

/**
 * @param modifier
 * @param widthSizeClass
 * @param contentPadding
 * @param gridState
 * @param articles
 * @param advertisement
 */
@Composable
fun NewsFeedScreen(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass,
    contentPadding: PaddingValues,
    gridState: LazyStaggeredGridState,
    articles: List<Article>,
    advertisement: Advertisement
) {
    NewsFeedGrid(
        modifier = modifier,
        widthSizeClass = widthSizeClass,
        contentPadding = contentPadding,
        gridState = gridState,
        articles = articles,
        advertisement = advertisement,
    )
}

/**
 * Preview of [NewsFeedScreen] in light theme.
 */
@Preview(showBackground = false)
@Composable
fun NewsFeedScreenLightPreview() {
    TheTerminalTheme {
        NewsFeedScreen(
            widthSizeClass = WindowWidthSizeClass.Compact,
            contentPadding = PaddingValues(0.dp),
            gridState = rememberLazyStaggeredGridState(),
            articles = NewsFeedRepository.articleList,
            advertisement = NewsFeedRepository.rigaAdvertisement
        )
    }
}

/**
 * Preview of [NewsFeedScreen] in dark theme.
 */
@Preview(showBackground = false)
@Composable
fun NewsFeedScreenDarkPreview() {
    TheTerminalTheme(darkTheme = true) {
        NewsFeedScreen(
            widthSizeClass = WindowWidthSizeClass.Compact,
            contentPadding = PaddingValues(0.dp),
            gridState = rememberLazyStaggeredGridState(),
            articles = NewsFeedRepository.articleList,
            advertisement = NewsFeedRepository.rigaAdvertisement
        )
    }
}