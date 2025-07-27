package xyz.malefic._404

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color

/**
 * @property nearBackground A useful color to apply to a container that should differentiate itself from the background
 *   but just a little.
 */
class SitePalette(
    val nearBackground: Color,
    val cobweb: Color,
    val brand: Brand,
    val surface: Color,
    val surfaceVariant: Color,
    val outline: Color,
    val shadow: Color,
) {
    class Brand(
        val primary: Color = Color.rgb(0x3C83EF),
        val accent: Color = Color.rgb(0xF3DB5B),
        val primaryHover: Color = Color.rgb(0x2563EB),
        val primaryPressed: Color = Color.rgb(0x1D4ED8),
    )
}

object SitePalettes {
    val light =
        SitePalette(
            nearBackground = Color.rgb(0xF8FAFC),
            surface = Color.rgb(0xFFFFFF),
            surfaceVariant = Color.rgb(0xF1F5F9),
            outline = Color.rgb(0xE2E8F0),
            shadow = Color.rgba(15, 23, 42, 0.1f),
            cobweb = Colors.LightGray,
            brand =
                SitePalette.Brand(
                    primary = Color.rgb(0x4F46E5),
                    primaryHover = Color.rgb(0x4338CA),
                    primaryPressed = Color.rgb(0x3730A3),
                    accent = Color.rgb(0xF59E0B),
                ),
        )
    val dark =
        SitePalette(
            nearBackground = Color.rgb(0x1E293B),
            surface = Color.rgb(0x0F172A),
            surfaceVariant = Color.rgb(0x334155),
            outline = Color.rgb(0x475569),
            shadow = Color.rgba(0, 0, 0, 0.3f),
            cobweb = Colors.LightGray.inverted(),
            brand =
                SitePalette.Brand(
                    primary = Color.rgb(0x6366F1),
                    primaryHover = Color.rgb(0x8B5CF6),
                    primaryPressed = Color.rgb(0xA78BFA),
                    accent = Color.rgb(0xFBBF24),
                ),
        )
}

fun ColorMode.toSitePalette(): SitePalette =
    when (this) {
        ColorMode.LIGHT -> SitePalettes.light
        ColorMode.DARK -> SitePalettes.dark
    }

@InitSilk
fun initTheme(ctx: InitSilkContext) {
    ctx.theme.palettes.light.background = Color.rgb(0xF8FAFC)
    ctx.theme.palettes.light.color = Color.rgb(0x1E293B)
    ctx.theme.palettes.dark.background = Color.rgb(0x0F172A)
    ctx.theme.palettes.dark.color = Color.rgb(0xF1F5F9)
}
