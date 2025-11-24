@file:OptIn(ExperimentalMaterial3Api::class)

package ua.hayden.theterminal.ui.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ua.hayden.theterminal.R

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
fun TerminalTopAppBar(
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
                    text = stringResource(R.string.app_name).uppercase(),
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