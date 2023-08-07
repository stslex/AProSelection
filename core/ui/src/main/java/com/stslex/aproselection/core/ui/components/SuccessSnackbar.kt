package com.stslex.aproselection.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.aproselection.core.ui.theme.AppTheme

@Composable
fun SuccessSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
) {
    BaseSnackbar(
        modifier = modifier,
        snackbarData = snackbarData
    )
}

@Preview(
    device = "id:pixel_6a", showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun SuccessSnackbarPreview() {
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
            SuccessSnackbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                snackbarData = data
            )
        }
    }
}