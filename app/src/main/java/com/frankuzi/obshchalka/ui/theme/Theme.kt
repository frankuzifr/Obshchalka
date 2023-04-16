package com.frankuzi.obshchalka.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Gray900,
    primaryVariant = Gray900,
    secondary = Gray800,
    background = Gray900,
//    surface = Color.White,
    onPrimary = White,
//    onSecondary = Color.Black,
    onBackground = BlueGray900,
//    onSurface = Color.Black,
)

private val LightColorPalette = lightColors(
    primary = Red500,
    primaryVariant = Purple700,
    secondary = Red300,
    background = Red500,
//    surface = Color.White,
    onPrimary = Color.Black,
//    onSecondary = Color.Black,
    onBackground = White,
//    onSurface = Color.Black,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ObshchalkaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}