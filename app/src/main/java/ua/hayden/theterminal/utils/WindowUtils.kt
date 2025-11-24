package ua.hayden.theterminal.utils

import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

private const val WINDOW_WIDTH_COMPACT = 600

internal fun WindowWidthSizeClass.toGridColumnCount() = when (this) {
    WindowWidthSizeClass.Compact -> 1
    WindowWidthSizeClass.Medium -> 2
    WindowWidthSizeClass.Expanded -> 3
    else -> 1
}

internal fun ComponentActivity.enableUnspecifiedOrientation() {
    val screenWidthDp = resources.configuration.screenWidthDp
    if (screenWidthDp >= WINDOW_WIDTH_COMPACT) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }
}