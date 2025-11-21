package ua.hayden.theterminal.ui.newsfeed.components.article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.OpenInNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import ua.hayden.theterminal.R
import ua.hayden.theterminal.ui.theme.AppDimens

/**
 * Displays a fade overlay at the bottom of an article card to indicate that more content
 * is available when the card is collapsed.
 *
 * The overlay uses a vertical gradient from transparent to the card background color.
 * It is only visible when [isContentCollapsed] is true.
 *
 * @param modifier Modifier applied to the overlay
 * @param isContentCollapsed Determines whether the overlay should be shown.
 */
@Composable
fun ArticleFadeOverlay(modifier: Modifier = Modifier, isContentCollapsed: Boolean) {
    if (isContentCollapsed) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(AppDimens.FadeOverlayHeight)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            CardDefaults.cardColors().containerColor
                        )
                    )
                )
        )
    }
}

/**
 * Displays a centered text followed by a horizontal divider, with adjustable spacing.
 *
 * - If [adaptiveDivider] is true and the text fits on a single line, the divider
 *   matches the text width.
 * - Otherwise, the divider spans 50% of the available width.
 *
 * @param modifier Modifier applied to the container.
 * @param space The vertical space between the text and the divider.
 * @param adaptiveDivider If true, the divider adapts to the text width for single-line text.
 * @param text The text to display above the divider.
 * @param style The [TextStyle] applied to the text.
 */
@Composable
fun TextWithDivider(
    modifier: Modifier = Modifier,
    space: Dp = AppDimens.SpacerSizeSmall,
    adaptiveDivider: Boolean,
    text: String,
    style: TextStyle
) {
    var singleLine by remember { mutableStateOf(true) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space),
        modifier = modifier
            .then(
                if (!adaptiveDivider) Modifier
                else Modifier.width(IntrinsicSize.Max).height(IntrinsicSize.Max)
            )
    ) {
        Text(
            text = text,
            style = style,
            onTextLayout = { singleLine = it.lineCount == 1 }
        )
        HorizontalDivider(
            color = LocalContentColor.current,
            modifier = Modifier.then(
                if (adaptiveDivider && singleLine) Modifier else Modifier.fillMaxWidth(0.5f)
            )
        )
    }
}

/**
 * Displays a "Read More" button with an icon, used to reveal the full article content.
 *
 * The button is only shown when [isContentExpanded] is true.
 *
 * @param modifier Modifier applied to the button
 * @param onClick Lambda invoked when the user clicks the button.
 * @param isContentExpanded Controls whether the button is visible.
 */
@Composable
fun ReadMoreButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isContentExpanded: Boolean
) {
    if (isContentExpanded) {
        Button(
            onClick = onClick,
            modifier = modifier
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.OpenInNew,
                contentDescription = null,
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(stringResource(R.string.label_read_more))
        }
    }
}