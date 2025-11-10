package ua.hayden.theterminal.ui.newsfeed

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.OpenInNew
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import eu.wewox.textflow.material3.TextFlow
import eu.wewox.textflow.material3.TextFlowObstacleAlignment
import ua.hayden.theterminal.R
import ua.hayden.theterminal.model.Article
import ua.hayden.theterminal.model.NewsFeedRepository
import ua.hayden.theterminal.ui.theme.TheTerminalTheme
import ua.hayden.theterminal.ui.theme.article

/**
 * Displays the full content of an [Article].
 *
 * The layout dynamically adapts to window size and card expansion state:
 * - In compact mode, only the headline, subheadline, and image are visible by default.
 *   Additional content (byline, image caption, article body, and “Read More” button) becomes
 *   visible when the card is expanded.
 * - In larger window sizes, all content is displayed by default.
 *
 * Includes animated content resizing when expanding or collapsing.
 *
 * @param modifier
 * @param isWindowCompact Defines whether the current window size class is [WindowWidthSizeClass.Compact].
 * @param isCardExpanded Controls visibility of extended article content when in compact mode.
 * @param article The [Article] data displayed inside the content layout.
 */
@Composable
fun ArticleContent(
    modifier: Modifier = Modifier,
    isWindowCompact: Boolean,
    isCardExpanded: Boolean,
    article: Article
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ) {
        // Headline
        TextWithDivider(
            modifier = Modifier.fillMaxWidth(),
            spaceBetween = dimensionResource(R.dimen.padding_medium),
            text = stringResource(article.headline),
            style = MaterialTheme.typography.article.headline,
            adaptiveDivider = false
        )
        Spacer(Modifier.size(dimensionResource(R.dimen.padding_medium)))

        // Subheadline
        TextWithDivider(
            modifier = Modifier.fillMaxWidth(),
            spaceBetween = dimensionResource(R.dimen.padding_medium),
            text = stringResource(article.subheadline),
            style = MaterialTheme.typography.article.subheadline,
            adaptiveDivider = false
        )
        Spacer(Modifier.size(dimensionResource(R.dimen.padding_medium)))

        VisibleSection(!isWindowCompact || isCardExpanded) {
            BylineText(article = article)
            Spacer(Modifier.size(dimensionResource(R.dimen.padding_small)))
        }

        ArticleImage(article = article)

        VisibleSection(!isWindowCompact || isCardExpanded) {
            Spacer(Modifier.size(dimensionResource(R.dimen.padding_small)))

            // Image caption
            TextWithDivider(
                modifier = Modifier.fillMaxWidth(),
                spaceBetween = dimensionResource(R.dimen.padding_small),
                text = stringResource(article.imageCaption),
                style = MaterialTheme.typography.article.imageCaption,
                adaptiveDivider = true
            )
            Spacer(Modifier.size(dimensionResource(R.dimen.padding_medium)))

            // Article body text
            Text(
                text = stringResource(article.text) + "…",
                style = MaterialTheme.typography.article.body
            )
            Spacer(Modifier.size(dimensionResource(R.dimen.padding_medium)))

            ReadMoreButton(onClick = { })
        }
    }
}

@Composable
inline fun VisibleSection(visible: Boolean, content: @Composable () -> Unit) {
    if (visible) content()
}

/**
 * Displays a centered text followed by a divider, separated by adjustable spacing.
 *
 * Typically used to visually separate sections or emphasize labeled content.
 *
 * @param modifier
 * @param spaceBetween The vertical space between the text and the divider.
 * @param text The text to display above the divider.
 * @param style The [TextStyle] applied to the text.
 */
@Composable
fun TextWithDivider(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle,
    adaptiveDivider: Boolean,
    spaceBetween: Dp,

) {
    var textWidth by remember { mutableStateOf(0) }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = text,
            style = style,
            modifier = Modifier.onGloballyPositioned{ textWidth = it.size.width }
        )
        Spacer(Modifier.size(spaceBetween))
        HorizontalDivider(
            color = LocalContentColor.current,
            modifier = Modifier.then(
                if (adaptiveDivider) {
                    Modifier.width(with(LocalDensity.current) { textWidth.toDp() })
                } else {
                    Modifier.fillMaxWidth(0.5f)
                }
            )
        )
    }
}

/**
 * Displays the byline section of an article, showing the author and news organization.
 *
 * @param modifier
 * @param article The [Article] providing author and news organization data.
 */
@Composable
fun BylineText(
    modifier: Modifier = Modifier,
    article: Article
) {
    Text(
        text = buildAnnotatedString {
            // article author
            withStyle(MaterialTheme.typography.article.byline.toSpanStyle()) {
                append(stringResource(R.string.label_byline))
                append(" ")
                append(stringResource(article.author))
            }
            append("\n")
            // news org
            withStyle(MaterialTheme.typography.article.newsOrg.toSpanStyle()) {
                append(stringResource(R.string.label_staff_reporter_of))
                append(" ")
            }
            withStyle(MaterialTheme.typography.article.newsOrgSC.toSpanStyle()) {
                append(stringResource(article.newsOrg))
            }
        },
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
}

/**
 * Displays an article image that adapts its width based on orientation.
 *
 * @param modifier
 * @param article The [Article] providing image resource.
 */
@Composable
fun ArticleImage(
    modifier: Modifier = Modifier,
    article: Article
) {
    val image = painterResource(article.image)
    val isPortrait = image.intrinsicSize.height > image.intrinsicSize.width

    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(LocalContentColor.current),
        modifier = modifier.fillMaxWidth(if (isPortrait) 0.5f else 1f)
    )
}

/**
 * Displays a button that opens the full article content.
 *
 * @param modifier
 * @param onClick Invoked when the user requests to read the full article.
 */
@Composable
fun ReadMoreButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
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

//@Composable
//fun TextFlowExample(article: Article) {
//    TextFlow(
//        text = stringResource(article.text),
//        style = MaterialTheme.typography.article.body,
//        modifier = Modifier.padding(16.dp),
//        obstacleAlignment = TextFlowObstacleAlignment.TopStart,
//        obstacleContent = {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.padding(8.dp)
//            ) {
//                ArticleImage(
//                    Modifier,
//                        //.padding(16.dp),
//                    article
//                )
//                TextWithDivider(
//                    modifier = Modifier,
//                    spaceBetween = dimensionResource(R.dimen.padding_small),
//                    text = stringResource(article.imageCaption),
//                    style = MaterialTheme.typography.article.imageCaption,
//                    adaptiveDivider = true
//                )
//            }
//        }
//    )
//}
//
//@Preview
//@Composable
//fun ExampleLightPreview() {
//    TheTerminalTheme {
//        Surface {
//            TextFlowExample(NewsFeedRepository.articleList[2])
//        }
//    }
//}

/**
 * Preview of [ArticleContent] in light theme.
 */
@Preview
@Composable
fun ArticleContentLightPreview() {
    TheTerminalTheme {
        Surface {
            ArticleContent(
                isWindowCompact = false,
                isCardExpanded = false,
                article = NewsFeedRepository.firstArticleExample
            )
        }
    }
}

/**
 * Preview of [ArticleContent] in dark theme.
 */
@Preview
@Composable
fun ArticleContentDarkPreview() {
    TheTerminalTheme(darkTheme = true) {
        Surface {
            ArticleContent(
                isWindowCompact = true,
                isCardExpanded = false,
                article = NewsFeedRepository.firstArticleExample
            )
        }
    }
}