package com.stslex.aproselection.feature.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.home.ui.model.UserUi

@Composable
fun SelectUsersListItem(
    user: UserUi?,
    modifier: Modifier = Modifier
) {
    val userTitle = user?.let {
        it.nickname.ifBlank { it.username }
    }.orEmpty()

    Box(
        modifier = modifier
            .padding(AppDimens.Padding.small)
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(AppDimens.Corners.medium),
            )
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(AppDimens.Corners.medium)
            )
            .clickable { },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(AppDimens.Padding.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(AppDimens.Padding.big))
            Text(
                modifier = Modifier.weight(1f),
                text = userTitle,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
@Preview
fun SelectUsersListItemPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            SelectUsersListItem(
                user = UserUi("uuid", "username", "nikname")
            )
        }
    }
}