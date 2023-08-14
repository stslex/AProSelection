package com.stslex.aproselection.ui.components

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.stslex.aproselection.core.ui.theme.AppTheme
import kotlin.math.sqrt

enum class MenuIconState {
    OPEN,
    CLOSE
}

@Composable
fun MenuIcon(
    onClickOpen: () -> Unit,
    onClickClose: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 32.dp,
    roundCorners: Dp = 4.dp,
    containerColorOpen: Color = MaterialTheme.colorScheme.surface,
    contentColorOpen: Color = MaterialTheme.colorScheme.onSurface,
    containerColorClose: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColorClose: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    val localHaptic = LocalHapticFeedback.current
    var currentState by remember {
        mutableStateOf(MenuIconState.OPEN)
    }

    val containerColor by animateColorAsState(
        targetValue = when (currentState) {
            MenuIconState.OPEN -> containerColorOpen
            MenuIconState.CLOSE -> containerColorClose
        },
        animationSpec = tween(900),
        label = "animContainerColor"
    )

    val contentColor by animateColorAsState(
        targetValue = when (currentState) {
            MenuIconState.OPEN -> contentColorOpen
            MenuIconState.CLOSE -> contentColorClose
        },
        animationSpec = tween(900),
        label = "animContentColor"
    )

    val animProgress by animateFloatAsState(
        targetValue = when (currentState) {
            MenuIconState.OPEN -> 0f
            MenuIconState.CLOSE -> 1f
        },
        animationSpec = tween(900),
        label = "animState"
    )

    Canvas(
        modifier = modifier
            .size(iconSize)
            .clip(RoundedCornerShape(roundCorners))
            .clickable(
                onClick = {
                    localHaptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    currentState = when (currentState) {
                        MenuIconState.OPEN -> {
                            onClickOpen()
                            MenuIconState.CLOSE
                        }

                        MenuIconState.CLOSE -> {
                            onClickClose()
                            MenuIconState.OPEN
                        }
                    }
                }
            )
            .background(containerColor)
            .padding(roundCorners)
    ) {

        val lineHeight = size.height * .3f
        val lineWidth =
            (size.width) * (sqrt(2f) * animProgress).coerceAtLeast(1f) - lineHeight * sqrt(2f) * animProgress

        val lineSize = Size(
            width = lineWidth,
            height = lineHeight
        )

        clipRect(
            left = 0f,
            right = size.width,
            top = 0f,
            bottom = size.height
        ) {

            for (index in 0 until 3) {

                val currentLineSize = if (index == 1) {
                    lineSize.copy(
                        width = size.width * (1 - animProgress)
                    )
                } else {
                    lineSize
                }
                val y = when (index) {
                    0 -> 0f
                    1 -> size.height * .5f - lineSize.height * .5f
                    2 -> size.height - lineSize.height
                    else -> 0f
                }
                val offset = Offset(
                    x = if (index == 1) {
                        size.width * animProgress * .5f
                    } else {
                        lineHeight * animProgress * .5f
                    },
                    y = y
                )
                val pivot = if (index == 0) {
                    offset.copy(
                        y = lineHeight * .5f
                    )
                } else if (index == 2) {
                    offset.copy(
                        y = size.height - lineHeight * .5f
                    )
                } else {
                    offset
                }
                rotate(
                    degrees = when (index) {
                        0 -> animProgress * 45f
                        1 -> 0f
                        2 -> -(animProgress * 45f)
                        else -> 0f
                    },
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
                onClickOpen = {},
                onClickClose = {},
                iconSize = 200.dp,
                roundCorners = 16.dp
            )

            MenuIcon(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(64.dp),
                onClickOpen = {},
                onClickClose = {},
            )
        }
    }
}