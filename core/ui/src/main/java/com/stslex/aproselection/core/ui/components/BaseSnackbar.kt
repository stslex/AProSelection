package com.stslex.aproselection.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.core.ui.theme.AppTheme

@Composable
fun BaseSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    containerColor: Color = SnackbarDefaults.color,
    contentColor: Color = SnackbarDefaults.contentColor,
    icon: (@Composable () -> Unit)? = null
) {
    Snackbar(
        modifier = modifier
            .padding(AppDimens.Padding.medium)
            .clip(RoundedCornerShape(AppDimens.Corners.big)),
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        Row {
            icon?.let { iconFunction ->
                iconFunction()
                Spacer(modifier = Modifier.width(AppDimens.Padding.medium))
            }
            Text(
                text = snackbarData.visuals.message,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(
    device = "id:pixel_6a", showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun BaseSnackbarPreview() {
    val data = object : SnackbarData {
        override val visuals: SnackbarVisuals
            get() = object : SnackbarVisuals {
                override val actionLabel: String = "action label"
                override val duration: SnackbarDuration = SnackbarDuration.Short
                override val message: String = "message message message message message message"
                override val withDismissAction: Boolean = false
            }

        override fun dismiss() = Unit

        override fun performAction() = Unit
    }
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            BaseSnackbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                snackbarData = data
            )
        }
    }
}