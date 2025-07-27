package xyz.malefic._404.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.letterSpacing
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
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import xyz.malefic._404.toSitePalette

val NotFoundTextStyle =
    CssStyle {
        base {
            Modifier
                .fontSize(120.px)
                .fontWeight(FontWeight.Bold)
                .letterSpacing((-0.05).em)
                .color(colorMode.toPalette().color)
                .opacity(0.9)
                .transition(
                    Transition.of(TransitionProperty.All, 0.4.s),
                    Transition.of("transform", 0.3.s),
                )
        }
        hover {
            Modifier
                .transform { scale(1.05) }
                .opacity(1.0)
        }
    }

val EnhancedButtonStyle =
    CssStyle {
        base {
            val sitePalette = colorMode.toSitePalette()
            Modifier
                .backgroundColor(sitePalette.brand.primary)
                .color(Colors.White)
                .padding(leftRight = 32.px, topBottom = 16.px)
                .borderRadius(12.px)
                .cursor(Cursor.Pointer)
                .fontSize(16.px)
                .fontWeight(FontWeight.Medium)
                .letterSpacing(0.025.em)
                .boxShadow(
                    offsetX = 0.px,
                    offsetY = 4.px,
                    blurRadius = 16.px,
                    color = sitePalette.shadow,
                ).transition(
                    Transition.of("background-color", 0.2.s),
                    Transition.of("transform", 0.2.s),
                    Transition.of("box-shadow", 0.2.s),
                )
        }
        hover {
            val sitePalette = colorMode.toSitePalette()
            Modifier
                .backgroundColor(sitePalette.brand.primaryHover)
                .transform { scale(1.02) }
                .boxShadow(
                    offsetX = 0.px,
                    offsetY = 8.px,
                    blurRadius = 20.px,
                    color = sitePalette.shadow,
                )
        }
    }

val NotFoundCardStyle =
    CssStyle {
        val sitePalette = colorMode.toSitePalette()
        Modifier
            .backgroundColor(sitePalette.surface)
            .borderRadius(24.px)
            .padding(48.px)
            .boxShadow(
                offsetX = 0.px,
                offsetY = 20.px,
                blurRadius = 40.px,
                color = sitePalette.shadow,
            )
    }

val FadeInAnimation =
    CssStyle {
        base {
            Modifier
                .opacity(0)
                .transform { translateY(30.px) }
                .transition(
                    Transition.of("opacity", 0.5.s),
                    Transition.of("transform", 0.5.s),
                )
        }
    }

val FadeInActiveAnimation =
    CssStyle {
        base {
            Modifier
                .opacity(1)
                .transform { translateY(0.px) }
        }
    }

@Page
@Composable
fun NotFoundPage() {
    val palette = ColorMode.current.toPalette()
    val sitePalette = ColorMode.current.toSitePalette()

    // Animation state control
    var isVisible by remember { mutableStateOf(false) }

    // Trigger the animation after the component is first composed
    LaunchedEffect(Unit) {
        delay(100)
        isVisible = true
    }

    Box(
        Modifier
            .fillMaxSize()
            .backgroundColor(palette.background)
            .display(DisplayStyle.Flex)
            .justifyContent(JustifyContent.Center)
            .alignItems(AlignItems.Center)
            .padding(20.px),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            NotFoundCardStyle
                .toModifier()
                .maxWidth(600.px)
                .then(FadeInAnimation.toModifier())
                .then(if (isVisible) FadeInActiveAnimation.toModifier() else Modifier),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // 404 Text with enhanced animation
                SpanText(
                    "404",
                    NotFoundTextStyle
                        .toModifier()
                        .then(FadeInAnimation.toModifier())
                        .then(
                            if (isVisible) {
                                FadeInActiveAnimation.toModifier().transition(
                                    Transition.of("all", 0.5.s, delay = 0.1.s),
                                )
                            } else {
                                Modifier
                            },
                        ),
                )

                // Page Not Found Text
                SpanText(
                    "Page Not Found",
                    Modifier
                        .fontSize(42.px)
                        .fontWeight(FontWeight.SemiBold)
                        .color(palette.color)
                        .letterSpacing((-0.02).em)
                        .margin(bottom = 24.px)
                        .textAlign(TextAlign.Center)
                        .then(FadeInAnimation.toModifier())
                        .then(
                            if (isVisible) {
                                FadeInActiveAnimation.toModifier().transition(
                                    Transition.of("all", 0.5.s, delay = 0.2.s),
                                )
                            } else {
                                Modifier
                            },
                        ),
                )

                // Enhanced Description
                SpanText(
                    "The page you're looking for doesn't exist. It might have been moved, deleted, or you entered the wrong URL.",
                    Modifier
                        .fontSize(18.px)
                        .color(palette.color.toRgb().copyf(alpha = 0.7f))
                        .textAlign(TextAlign.Center)
                        .margin(bottom = 40.px)
                        .maxWidth(480.px)
                        .then(FadeInAnimation.toModifier())
                        .then(
                            if (isVisible) {
                                FadeInActiveAnimation.toModifier().transition(
                                    Transition.of("all", 0.5.s, delay = 0.3.s),
                                )
                            } else {
                                Modifier
                            },
                        ),
                )

                // Enhanced Home Button
                Button(
                    onClick = { window.location.href = "https://afwg.malefic.xyz" },
                    EnhancedButtonStyle
                        .toModifier()
                        .then(FadeInAnimation.toModifier())
                        .then(
                            if (isVisible) {
                                FadeInActiveAnimation.toModifier().transition(
                                    Transition.of("all", 0.5.s, delay = 0.4.s),
                                )
                            } else {
                                Modifier
                            },
                        ),
                ) {
                    SpanText("Back to Homepage")
                }
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
