package xyz.malefic._404.pages

import androidx.compose.runtime.Composable
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
import com.varabyte.kobweb.compose.ui.graphics.Color as ComposeColor

val TextStyle =
    CssStyle {
        base {
            Modifier
                .fontSize(120.px)
                .fontWeight(FontWeight.Bold)
                .color(ComposeColor.rgb(31, 41, 55))
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
                .backgroundColor(ComposeColor.rgb(79, 70, 229))
                .color(Colors.White)
                .padding(leftRight = 24.px, topBottom = 12.px)
                .borderRadius(8.px)
                .cursor(Cursor.Pointer)
                .transition(Transition.of("background-color", 0.2.s))
        }
        hover {
            Modifier.backgroundColor(ComposeColor.rgb(67, 56, 202))
        }
    }

@Page
@Composable
fun NotFoundPage() {
    Box(
        Modifier
            .fillMaxSize()
            .backgroundColor(ComposeColor.rgb(249, 250, 251))
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
                    .color(ComposeColor.rgb(31, 41, 55))
                    .margin(bottom = 16.px),
            )

            // Description
            SpanText(
                "Sorry, we couldn't find the page you're looking for. Please check the URL or go back to the homepage.",
                Modifier
                    .fontSize(16.px)
                    .color(ComposeColor.rgb(107, 114, 128))
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
