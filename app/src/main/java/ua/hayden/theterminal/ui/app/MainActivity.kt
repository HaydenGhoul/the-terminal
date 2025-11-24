package ua.hayden.theterminal.ui.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.utils.enableUnspecifiedOrientation
import ua.hayden.theterminal.viewmodel.NewsFeedViewModel
import ua.hayden.theterminal.viewmodel.newsFeedViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: NewsFeedViewModel by viewModels { newsFeedViewModelFactory }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        enableUnspecifiedOrientation()

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this@MainActivity)
            val widthSizeClass = windowSizeClass.widthSizeClass

            TheTerminalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TheTerminalApp(widthSizeClass = widthSizeClass, viewModel = viewModel)
                }
            }
        }
    }
}