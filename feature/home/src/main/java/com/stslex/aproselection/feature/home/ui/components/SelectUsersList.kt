package com.stslex.aproselection.feature.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.stslex.aproselection.core.ui.base.BasePager.DEFAULT_PAGING_PAGE_SIZE
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.home.ui.model.UserUi
import com.stslex.aproselection.feature.home.ui.usersPagingPreview

@Composable
fun SelectUsersList(
    users: LazyPagingItems<UserUi>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                vertical = AppDimens.Padding.large,
                horizontal = AppDimens.Padding.small
            )
            .background(
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                shape = RoundedCornerShape(AppDimens.Corners.big)
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(AppDimens.Padding.medium),
            userScrollEnabled = users.loadState.refresh != LoadState.Loading
        ) {
            if (users.loadState.refresh == LoadState.Loading) {
                items(
                    count = DEFAULT_PAGING_PAGE_SIZE,
                    key = { index -> index }
                ) {
                    SelectUsersListItem(UserUi.EMPTY)
                }
            } else {
                items(
                    count = users.itemCount,
                    contentType = users.itemContentType { "user" },
                    key = users.itemKey { item -> item.uuid }
                ) { index ->
                    val item = checkNotNull(users[index]) { "item couldn't be null" }
                    SelectUsersListItem(item)
                }
            }
        }
    }
}

@Preview
@Composable
fun SelectUsersListPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            SelectUsersList(
                users = remember { usersPagingPreview }.collectAsLazyPagingItems()
            )
        }
    }
}