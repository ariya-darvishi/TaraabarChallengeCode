package net.taraabar.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import net.taraabar.designsystem.R


fun getFontFamilyYekanBakh(): FontFamily {
    return FontFamily(
        Font(resId = R.font.yekan_bakh_regular, weight = FontWeight.Normal),
        Font(resId = R.font.yekan_bakh_light, weight = FontWeight.Light),
        Font(resId = R.font.yekan_bakh_bold,weight = FontWeight.Bold),
        Font(resId = R.font.yekan_bakh_semibold,weight = FontWeight.SemiBold),
    )
}

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)