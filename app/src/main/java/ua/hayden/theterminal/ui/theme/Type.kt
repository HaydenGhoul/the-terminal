package ua.hayden.theterminal.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ua.hayden.theterminal.R

val playfairDisplay = FontFamily(
    Font(R.font.playfair_display_regular),
    Font(R.font.playfair_display_bold, FontWeight.Bold),
    Font(R.font.playfair_display_semi_bold, FontWeight.SemiBold),
    Font(R.font.playfair_display_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic)
)

val sourceSerif4 = FontFamily(
    Font(R.font.source_serif_4_regular),
    Font(R.font.source_serif_4_bold, FontWeight.Bold),
    Font(R.font.source_serif_4_italic, style = FontStyle.Italic),
    Font(R.font.source_serif_4_bold_italic, FontWeight.Bold, FontStyle.Italic)

)

val ebGaramond = FontFamily(
    Font(R.font.eb_garamond_italic, style = FontStyle.Italic)
)

// Set of Material typography styles to start with
val AppTypography = Typography(
    // Label: button
    labelLarge = TextStyle(
        fontFamily = sourceSerif4,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp,
        fontFeatureSettings = "smcp",
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        textAlign = TextAlign.Center
    )
)


// Top app bar masthead, default style: titleLarge
val Typography.masthead: TextStyle
    @Composable get() = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        textAlign = TextAlign.Center
    )

// Top app bar submasthead, default style:
val Typography.submasthead: TextStyle
    @Composable get() = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        textAlign = TextAlign.Center
    )

// Article headline, default style: headlineLarge
val Typography.headlineArticle: TextStyle
    @Composable get() = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Italic,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp,
        textAlign = TextAlign.Start
    )

// Article subheadline, default style: headlineSmall
val Typography.subheadlineArticle: TextStyle
    @Composable get() = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp,
        textAlign = TextAlign.Center
    )

// Label: By {article author}, default style: labelLarge
val Typography.labelArticleAuthor: TextStyle
    @Composable get() = TextStyle(
        fontFamily = sourceSerif4,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp,
        fontFeatureSettings = "smcp",
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        textAlign = TextAlign.Center
    )

// Label: Stuff reporter of {news org}, default style: labelMedium
val Typography.labelNewsOrg: TextStyle
    @Composable get() = TextStyle(
        fontFamily = sourceSerif4,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

// Label: Stuff reporter of {news org}, default style: labelMedium
val Typography.labelNewsOrgSC: TextStyle
    @Composable get() = TextStyle(
        fontFamily = sourceSerif4,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 12.sp,
        fontFeatureSettings = "smcp",
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

// Image description, default style: labelLarge
val Typography.labelImageDescription: TextStyle
    @Composable get() = TextStyle(
        fontFamily = sourceSerif4,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        textAlign = TextAlign.Center
    )

// Article text, default style: bodyLarge
val Typography.bodyArticle: TextStyle
    @Composable get() = TextStyle(
        fontFamily = sourceSerif4,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        textAlign = TextAlign.Justify
    )

// Label copyright, default style: labelSmall
val Typography.labelCopyright: TextStyle
    @Composable get() = TextStyle(
        fontFamily = ebGaramond,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        textAlign = TextAlign.Center
    )