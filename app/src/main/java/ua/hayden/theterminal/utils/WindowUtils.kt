package ua.hayden.theterminal.utils

import android.content.pm.ActivityInfo
import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

internal fun WindowWidthSizeClass.toColumnCount() = when (this) {
    WindowWidthSizeClass.Compact -> 1
    WindowWidthSizeClass.Medium -> 2
    WindowWidthSizeClass.Expanded -> 3
    else -> 1
}

internal fun ComponentActivity.lockOrientationCompact() {
    val screenWidthDp = resources.configuration.screenWidthDp
    if (screenWidthDp >= 600) requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
}