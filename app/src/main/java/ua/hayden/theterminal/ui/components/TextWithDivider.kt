package ua.hayden.theterminal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import ua.hayden.theterminal.ui.theme.AppDimens

/**
 * Displays a centered text followed by a horizontal divider, with adjustable spacing.
 *
 * - If [isAdaptiveDivider] is true and the text fits on a single line, the divider
 *   matches the text width.
 * - Otherwise, the divider spans 50% of the available width.
 *
 * @param modifier Modifier applied to the container.
 * @param verticalSpacing The vertical space between the text and the divider.
 * @param isAdaptiveDivider If true, the divider adapts to the text width for single-line text.
 * @param text The text to display above the divider.
 * @param style The [TextStyle] applied to the text.
 */
@Composable
fun TextWithDivider(
    modifier: Modifier = Modifier,
    verticalSpacing: Dp = AppDimens.SpacerSizeSmall,
    isAdaptiveDivider: Boolean,
    text: String,
    style: TextStyle
) {
    var singleLine by remember { mutableStateOf(true) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(verticalSpacing),
        modifier = modifier
            .then(
                if (!isAdaptiveDivider) Modifier
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
                if (isAdaptiveDivider && singleLine) Modifier else Modifier.fillMaxWidth(0.5f)
            )
        )
    }
}