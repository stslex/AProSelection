package com.stslex.aproselection.feature.auth.ui.components

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.aproselection.core.ui.ext.noRippleClick
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.AuthFieldsState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AuthTitle(
    swipeableState: SwipeableState<AuthFieldsState>,
    modifier: Modifier = Modifier
) {
    val progress = swipeableState.progress.fraction
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(AppDimens.Padding.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AuthFieldsState.entries.forEach { item ->
            val scaleValue by animateFloatAsState(
                targetValue = if (item == swipeableState.progress.to) {
                    (progress * 1.5f)
                } else {
                    (1 - progress)
                }.coerceIn(.6f, 1f)
            )
            val scaleDividerValue by animateFloatAsState(
                targetValue = if (item == swipeableState.progress.to) {
                    (progress * 1.5f)
                } else {
                    (1 - progress)
                }.coerceIn(.1f, 1f)
            )
            val coroutineScope = rememberCoroutineScope()
            Column(
                modifier = Modifier
                    .noRippleClick {
                        coroutineScope.launch {
                            swipeableState.animateTo(item)
                        }
                    }
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.scale(scaleValue),
                    text = stringResource(id = item.titleResId),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = scaleValue
                    ),
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
                Divider(
                    modifier = Modifier
                        .padding(top = AppDimens.Padding.small)
                        .width(100.dp * scaleDividerValue)
                        .clip(RoundedCornerShape(AppDimens.Padding.smallest)),
                    thickness = 4.dp * scaleValue,
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = scaleValue
                    )
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(
    device = "id:pixel_6", showSystemUi = true, showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun AuthScreenPreview() {
    AppTheme {
        var currentItem by remember {
            mutableStateOf(AuthFieldsState.AUTH)
        }
        val swipeableState = rememberSwipeableState(
            initialValue = AuthFieldsState.AUTH
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            AuthTitle(
                modifier = Modifier.align(Alignment.TopCenter),
                swipeableState = swipeableState,
            )
        }
    }
}