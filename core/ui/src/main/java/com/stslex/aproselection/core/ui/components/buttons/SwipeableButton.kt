package com.stslex.aproselection.core.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.aproselection.core.ui.ext.toPx
import com.stslex.aproselection.core.ui.theme.AppTheme
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun SwipeableButton(
    modifier: Modifier = Modifier,
) {
    val stepsButtonCount = 10
    val stepsContainerSize = 10
    val containerSizeInit = 100.dp.toPx
    val containerSizeStep = 10.dp.toPx

    val stepsCircleSize = 20
    val circleSizeInit = 4.dp.toPx
    val circleSizeStep = 1.dp.toPx

    var containerInputSize by remember {
        mutableFloatStateOf(0f)
    }

    val containerSize by remember {
        derivedStateOf {
            containerSizeInit + (containerInputSize * stepsContainerSize) * containerSizeStep
        }
    }

    var circleInputSize by remember {
        mutableFloatStateOf(0f)
    }

    val circleSize by remember {
        derivedStateOf {
            circleSizeInit + (circleInputSize * stepsCircleSize) * circleSizeStep
        }
    }

    var inputCount by remember {
        mutableFloatStateOf(0f)
    }

    val buttonsCount by remember {
        derivedStateOf {
            (inputCount * stepsButtonCount).roundToInt()
        }
    }

    val radDiff = 360f / buttonsCount


    var dragAmountX by remember {
        mutableFloatStateOf(0f)
    }

    var dragAmountY by remember {
        mutableFloatStateOf(0f)
    }

    val dragAmount by remember {
        derivedStateOf {
            val min =
                if (
                    (dragAmountX < 0 && dragAmountY > 0) ||
                    (dragAmountY < 0 && dragAmountX > 0)
                ) {
                    -1
                } else {
                    1
                }
            min * sqrt(abs(dragAmountX * dragAmountY))
        }
    }


    Box(
        Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    dragAmountX = change.position.x
                    dragAmountY = change.position.y
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "x = $dragAmountX \ny = $dragAmountY \na = $dragAmount",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "count = $buttonsCount",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Slider(
                modifier = Modifier.padding(8.dp),
                value = inputCount,
                onValueChange = {
                    inputCount = it
                },
                steps = stepsButtonCount
            )
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "containerSize = $containerSize Px",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Slider(
                modifier = Modifier.padding(8.dp),
                value = containerInputSize,
                onValueChange = {
                    containerInputSize = it
                },
                steps = stepsContainerSize
            )
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "circleSize = $circleSize Px",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Slider(
                modifier = Modifier.padding(8.dp),
                value = circleInputSize,
                onValueChange = {
                    circleInputSize = it
                },
                steps = stepsCircleSize
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .size(with(LocalDensity.current) { containerSize.toDp() })
                    .align(Alignment.CenterHorizontally)
                    .drawBehind {
                        val radius = containerSize * .5f

                        for (index in 0 until buttonsCount) {

                            val radian = (index * radDiff + dragAmount) * (PI.toFloat() / 180f)

                            val xRad = sin(radian)
                            val x = xRad * radius + center.x

                            val yRad = cos(radian)
                            val y = yRad * radius + center.y

                            val offset = Offset(
                                x = x,
                                y = y
                            )

                            drawCircle(
                                color = Color.White,
                                center = offset,
                                radius = circleSize
                            )
                        }

                        drawCircle(
                            color = Color.White,
                            center = center,
                            radius = radius,
                            style = Stroke(1.dp.toPx())
                        )
                    },
            )
        }


    }

}

@Preview
@Composable
fun SwipeableButtonPreview() {
    AppTheme(true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            SwipeableButton(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}