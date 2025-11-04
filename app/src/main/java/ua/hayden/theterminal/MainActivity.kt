package ua.hayden.theterminal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.ArticleRepository
import ua.hayden.theterminal.ui.ArticleAdaptiveGrid
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.ui.theme.masthead
import ua.hayden.theterminal.ui.theme.submasthead
import ua.hayden.theterminal.viewmodel.ArticleViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheTerminalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TheTerminalApp(this)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun TheTerminalApp(
    activity: ComponentActivity,
    viewModel: ArticleViewModel = viewModel()
) {

    val articlesState by viewModel.articles
    val windowSizeClass = calculateWindowSizeClass(activity)
    val widthSizeClass = windowSizeClass.widthSizeClass

    Scaffold(
        topBar = { TheTerminalTopAppBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        TheTerminalScreen(
            widthSizeClass = widthSizeClass,
            contentPadding = innerPadding,
            articles = articlesState
        )
    }
}

@Composable
fun TheTerminalScreen(
    articles: List<Article>,
    widthSizeClass: WindowWidthSizeClass,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(R.dimen.padding_zero))
) {
    ArticleAdaptiveGrid(
        widthSizeClass = widthSizeClass,
        contentPadding = contentPadding,
        articles = articles
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheTerminalTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.app_name_uppercase),
                    style = MaterialTheme.typography.masthead
                )
                Text(
                    text = stringResource(R.string.edition_online),
                    style = MaterialTheme.typography.submasthead
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = false)
@Composable
fun TheTheTerminalAppLightPreview() {
    TheTerminalTheme {
        Scaffold(
            topBar = { TheTerminalTopAppBar() },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            TheTerminalScreen(
                widthSizeClass = WindowWidthSizeClass.Compact,
                contentPadding = innerPadding,
                articles = ArticleRepository.articleList
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun TheTheTerminalAppDarkPreview() {
    TheTerminalTheme(darkTheme = true) {
        Scaffold(
            topBar = { TheTerminalTopAppBar() },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            TheTerminalScreen(
                widthSizeClass = WindowWidthSizeClass.Compact,
                contentPadding = innerPadding,
                articles = ArticleRepository.articleList
            )
        }
    }
}



