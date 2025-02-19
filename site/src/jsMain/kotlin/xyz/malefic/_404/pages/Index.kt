package xyz.malefic._404.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.transform
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.browser.window
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import org.w3c.dom.MediaQueryList
import org.w3c.dom.events.Event
import com.varabyte.kobweb.compose.ui.graphics.Color as ComposeColor

object Theme {
    private var isDarkMode by mutableStateOf(false)
    private var mediaQuery: MediaQueryList? = null

    init {
        // Initialize the dark mode state based on system preference
        mediaQuery = window.matchMedia("(prefers-color-scheme: dark)")
        isDarkMode = mediaQuery?.matches ?: false

        // Add listener for system theme changes
        mediaQuery?.addEventListener("change", { event: Event ->
            val mediaQueryEvent = event.unsafeCast<MediaQueryList>()
            isDarkMode = mediaQueryEvent.matches
        })
    }

    // Colors for light theme
    private val lightColors =
        object {
            val background = ComposeColor.rgb(249, 250, 251)
            val text = ComposeColor.rgb(31, 41, 55)
            val secondaryText = ComposeColor.rgb(107, 114, 128)
            val primary = ComposeColor.rgb(79, 70, 229)
            val primaryHover = ComposeColor.rgb(67, 56, 202)
        }

    // Colors for dark theme
    private val darkColors =
        object {
            val background = ComposeColor.rgb(17, 24, 39)
            val text = ComposeColor.rgb(243, 244, 246)
            val secondaryText = ComposeColor.rgb(156, 163, 175)
            val primary = ComposeColor.rgb(99, 102, 241)
            val primaryHover = ComposeColor.rgb(129, 140, 248)
        }

    val backgroundColor get() = if (isDarkMode) darkColors.background else lightColors.background
    val textColor get() = if (isDarkMode) darkColors.text else lightColors.text
    val secondaryTextColor get() = if (isDarkMode) darkColors.secondaryText else lightColors.secondaryText
    val primaryColor get() = if (isDarkMode) darkColors.primary else lightColors.primary
    val primaryHoverColor get() = if (isDarkMode) darkColors.primaryHover else lightColors.primaryHover
}

val TextStyle =
    CssStyle {
        base {
            Modifier
                .fontSize(120.px)
                .fontWeight(FontWeight.Bold)
                .color(Theme.textColor)
                .opacity(0.8)
                .transition(Transition.of("transform", 0.3.s))
        }
        hover {
            Modifier.transform { scale(1.05) }
        }
    }

val ButtonStyle =
    CssStyle {
        base {
            Modifier
                .backgroundColor(Theme.primaryColor)
                .color(Colors.White)
                .padding(leftRight = 24.px, topBottom = 12.px)
                .borderRadius(8.px)
                .cursor(Cursor.Pointer)
                .transition(Transition.of("background-color", 0.2.s))
        }
        hover {
            Modifier.backgroundColor(Theme.primaryHoverColor)
        }
    }

@Page
@Composable
fun NotFoundPage() {
    Box(
        Modifier
            .fillMaxSize()
            .backgroundColor(Theme.backgroundColor)
            .display(DisplayStyle.Flex)
            .justifyContent(JustifyContent.Center)
            .alignItems(AlignItems.Center),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier =
                Modifier
                    .maxWidth(600.px)
                    .padding(20.px),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // 404 Text with animation
            SpanText(
                "404",
                TextStyle.toModifier(),
            )

            // Page Not Found Text
            SpanText(
                "Page Not Found",
                Modifier
                    .fontSize(36.px)
                    .fontWeight(FontWeight.Medium)
                    .color(Theme.textColor)
                    .margin(bottom = 16.px),
            )

            // Description
            SpanText(
                "Sorry, we couldn't find the page you're looking for. Please check the URL or go back to the homepage.",
                Modifier
                    .fontSize(16.px)
                    .color(Theme.secondaryTextColor)
                    .textAlign(TextAlign.Center)
                    .margin(bottom = 32.px),
            )

            // Home Button
            Button(
                onClick = { window.location.href = "https://afwg.malefic.xyz" },
                ButtonStyle.toModifier(),
            ) {
                SpanText(
                    "Back to Homepage",
                    Modifier
                        .fontSize(16.px)
                        .fontWeight(FontWeight.Medium),
                )
            }
        }
    }
}

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier =
            modifier
                .onClick { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}
