package com.stslex.aproselection.feature.auth.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.auth.ui.store.AuthStore

@Composable
fun AuthTitle(
    state: AuthStore.AuthFieldsState,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(
            top = AppDimens.Padding.big
        ),
        text = stringResource(id = state.titleResId),
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 1
    )
}

@Preview(
    device = "id:pixel_6", showSystemUi = true, showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun AuthScreenPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            AuthTitle(
                modifier = Modifier.align(Alignment.TopCenter),
                state = AuthStore.AuthFieldsState.AUTH
            )
        }
    }
}