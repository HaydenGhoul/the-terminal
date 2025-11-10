package ua.hayden.theterminal.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
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

val robotoCondensed = FontFamily(
    Font(R.font.roboto_condensed_regular),
    Font(R.font.roboto_condensed_bold, FontWeight.Bold)
)

val ebGaramond = FontFamily(
    Font(R.font.eb_garamond_italic, style = FontStyle.Italic)
)

@Immutable
data class ArticleTypography(
    val headline: TextStyle,     // Style: headlineLarge
    val subheadline: TextStyle,  // Style: headlineSmall
    val byline: TextStyle,       // Style: labelLarge
    val newsOrg: TextStyle,      // Style: labelMedium
    val newsOrgSC: TextStyle,    // Style: labelMedium
    val imageCaption: TextStyle, // Style: titleMedium
    val body: TextStyle,         // Style: bodyLarge
)

@Immutable
data class AdvertisementTypography(
    val headline: TextStyle, // Style: headlineMedium
    val caption: TextStyle,  // Style: headlineSmall
    val model: TextStyle,    // Style: titleLarge
)

// Set of Material typography styles to start with
val appTypography = Typography(
    // Masthead in top app bar
    titleLarge = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp,
        textAlign = TextAlign.Center
    ),
    // Buttons label
    labelLarge = TextStyle(
        fontFamily = sourceSerif4,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp,
        fontFeatureSettings = "smcp",
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        textAlign = TextAlign.Center
    ),
    // Edition label in top app bar
    labelMedium = TextStyle(
        fontFamily = playfairDisplay,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        textAlign = TextAlign.Center
    ),
    // Copyright label
    labelSmall = TextStyle(
        fontFamily = ebGaramond,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp,
        textAlign = TextAlign.Center
    )
)

val Typography.article: ArticleTypography
    get() = ArticleTypography(
        headline = TextStyle(
            fontFamily = playfairDisplay,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Italic,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
            textAlign = TextAlign.Start
        ),
        subheadline = TextStyle(
            fontFamily = playfairDisplay,
            fontWeight = FontWeight.SemiBold,
            fontStyle = FontStyle.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
            textAlign = TextAlign.Center
        ),
        byline = TextStyle(
            fontFamily = sourceSerif4,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp,
            fontFeatureSettings = "smcp",
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp,
            textAlign = TextAlign.Center
        ),
        newsOrg = TextStyle(
            fontFamily = sourceSerif4,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        newsOrgSC = TextStyle(
            fontFamily = sourceSerif4,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 12.sp,
            fontFeatureSettings = "smcp",
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        imageCaption = TextStyle(
            fontFamily = sourceSerif4,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            textAlign = TextAlign.Center
        ),
        body = TextStyle(
            fontFamily = sourceSerif4,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.3.sp,
            textAlign = TextAlign.Justify,
            textIndent = TextIndent(firstLine = 24.sp)
        )
    )

val Typography.advertisement: AdvertisementTypography
    get() = AdvertisementTypography(
        headline = TextStyle(
            fontFamily = robotoCondensed,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
            textAlign = TextAlign.Start
        ),
        caption = TextStyle(
            fontFamily = robotoCondensed,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp,
            textAlign = TextAlign.Start
        ),
        model = TextStyle(
            fontFamily = robotoCondensed,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 20.sp,
            lineHeight = 26.sp,
            letterSpacing = 0.sp,
            textAlign = TextAlign.Center
        )
    )