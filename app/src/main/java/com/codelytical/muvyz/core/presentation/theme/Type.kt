
package com.codelytical.muvyz.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.codelytical.muvyz.R


val quicksand = FontFamily(
    Font(R.font.quicksand_light, FontWeight.Light),
    Font(R.font.quicksand_regular, FontWeight.Normal),
    Font(R.font.quicksand_medium, FontWeight.Medium),
    Font(R.font.quicksand_semibold, FontWeight.SemiBold),
    Font(R.font.quicksand_bold, FontWeight.Bold)
)


val Typography = Typography().run {
    copy(
        displayLarge = displayLarge.copy(
            fontFamily = quicksand
        ),
        displayMedium = displayMedium.copy(
            fontFamily = quicksand
        ),
        displaySmall = displaySmall.copy(
            fontFamily = quicksand
        ),
        headlineLarge = headlineLarge.copy(
            fontFamily = quicksand
        ),
        headlineMedium = headlineMedium.copy(
            fontFamily = quicksand
        ),
        headlineSmall = headlineSmall.copy(
            fontFamily = quicksand
        ),
        titleLarge = titleLarge.copy(
            fontFamily = quicksand,
            fontWeight = FontWeight.Bold
        ),
        titleMedium = titleMedium.copy(
            fontFamily = quicksand,
            fontWeight = FontWeight.Bold
        ),
        titleSmall = titleSmall.copy(
            fontFamily = quicksand,
            fontWeight = FontWeight.Bold
        ),
        bodyLarge = bodyLarge.copy(
            fontFamily = quicksand
        ),
        bodyMedium = bodyMedium.copy(
            fontFamily = quicksand
        ),
        bodySmall = bodySmall.copy(
            fontFamily = quicksand
        ),
        labelLarge = labelLarge.copy(
            fontFamily = quicksand
        ),
        labelMedium = labelMedium.copy(
            fontFamily = quicksand
        ),
        labelSmall = labelSmall.copy(
            fontFamily = quicksand
        ),
    )
}
