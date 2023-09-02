package com.stslex.aproselection.feature.main.components.menu_icon

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.main.components.menu_icon.MenuIconLineType.BOTTOM
import com.stslex.aproselection.feature.main.components.menu_icon.MenuIconLineType.CENTER
import com.stslex.aproselection.feature.main.components.menu_icon.MenuIconLineType.TOP
import kotlin.math.sqrt

@Composable
fun MenuIcon(
    drawerState: AppDrawerState,
    onClick: (AppDrawerState) -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 32.dp,
    roundCorners: Dp = 4.dp,
    containerColorOpen: Color = MaterialTheme.colorScheme.surface,
    contentColorOpen: Color = MaterialTheme.colorScheme.onSurface,
    containerColorClose: Color = containerColorOpen,
    contentColorClose: Color = contentColorOpen,
    animationDuration: Int = 500
) {
    val localHaptic = LocalHapticFeedback.current

    val containerColor by animateColorAsState(
        targetValue = when (drawerState) {
            AppDrawerState.OPEN -> containerColorOpen
            AppDrawerState.CLOSE -> containerColorClose
        },
        animationSpec = tween(animationDuration),
        label = "animContainerColor"
    )

    val contentColor by animateColorAsState(
        targetValue = when (drawerState) {
            AppDrawerState.OPEN -> contentColorOpen
            AppDrawerState.CLOSE -> contentColorClose
        },
        animationSpec = tween(animationDuration),
        label = "animContentColor"
    )

    val animProgress by animateFloatAsState(
        targetValue = when (drawerState) {
            AppDrawerState.CLOSE -> 0f
            AppDrawerState.OPEN -> 1f
        },
        animationSpec = tween(animationDuration),
        label = "animState"
    )

    Canvas(
        modifier = modifier
            .size(iconSize)
            .clip(RoundedCornerShape(roundCorners))
            .clickable(
                onClick = {
                    localHaptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onClick(drawerState.inverse())
                }
            )
            .background(containerColor)
            .padding(roundCorners)
    ) {

        val lineHeight = size.height * .2f

        val sqrtTwo = sqrt(2f)
        val rotationPadding = lineHeight * sqrtTwo * animProgress
        val rotationWidthFract = (sqrtTwo * animProgress).coerceAtLeast(1f)
        val lineWidth = size.width * rotationWidthFract - rotationPadding

        val lineSize = Size(
            width = lineWidth,
            height = lineHeight
        )

        MenuIconLineType.entries.forEach { type ->
            val currentLineSize = when (type) {
                CENTER -> lineSize.copy(width = size.width * (1 - animProgress))
                BOTTOM, TOP -> lineSize
            }

            val yOffset = when (type) {
                TOP -> 0f
                CENTER -> (size.height - lineSize.height) * .5f
                BOTTOM -> size.height - lineSize.height
            }

            val xOffset = when (type) {
                CENTER -> size.width * animProgress * .5f
                BOTTOM, TOP -> lineHeight * animProgress * .5f
            }

            val offset = Offset(x = xOffset, y = yOffset)

            val pivot = when (type) {
                TOP -> offset.copy(y = lineHeight * .5f)
                CENTER -> offset
                BOTTOM -> offset.copy(y = size.height - lineHeight * .5f)
            }

            val degrees = when (type) {
                TOP -> animProgress * 45f
                CENTER -> 0f
                BOTTOM -> -animProgress * 45f
            }

            rotate(
                degrees = degrees,
                pivot = pivot
            ) {
                drawRoundRect(
                    color = contentColor,
                    topLeft = offset,
                    size = currentLineSize,
                    cornerRadius = CornerRadius(
                        x = lineHeight,
                        y = lineHeight
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun MenuIconPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            MenuIcon(
                onClick = {},
                iconSize = 200.dp,
                roundCorners = 16.dp,
                drawerState = AppDrawerState.OPEN
            )

            MenuIcon(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(64.dp),
                onClick = {},
                drawerState = AppDrawerState.OPEN
            )
        }
    }
}