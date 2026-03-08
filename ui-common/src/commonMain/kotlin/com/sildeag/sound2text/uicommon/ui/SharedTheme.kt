package sound2text.uicore.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun SharedTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Primary,
            secondary = Secondary,
            background = Background,
            surface = Surface,
            onPrimary = Color.White,
            onSecondary = Color.Black,
            onBackground = TextPrimary,
            onSurface = TextPrimary
        ),
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}