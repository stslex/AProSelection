package com.stslex.aproselection.feature.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.core.ui.theme.AppTheme
import com.stslex.aproselection.feature.home.ui.components.SelectUsersList
import com.stslex.aproselection.feature.home.ui.model.UserUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun HomeScreen(
    logOut: () -> Unit,
    users: () -> StateFlow<PagingData<UserUi>>,
    modifier: Modifier = Modifier
) {
    var isUsersOpen by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Success",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedButton(
                onClick = logOut
            ) {
                Text(text = "logout")
            }
        }

        AnimatedVisibility(
            visible = isUsersOpen
        ) {
            val lazyPagingItems = remember {
                users()
            }.collectAsLazyPagingItems()

            Dialog(
                onDismissRequest = {
                    isUsersOpen = false
                },
            ) {
                SelectUsersList(lazyPagingItems)
            }
        }


        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.BottomEnd),
            visible = isUsersOpen.not()
        ) {
            FloatingActionButton(
                modifier = Modifier.padding(AppDimens.Padding.medium),
                onClick = {
                    isUsersOpen = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    }
}

val usersPagingPreview = MutableStateFlow(
    PagingData.from(
        List(20) { index ->
            UserUi(
                uuid = "uuid$index",
                username = "username",
                nickname = "nickname"
            )
        }
    )
).asStateFlow()

@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(
            logOut = {},
            users = ::usersPagingPreview
        )
    }
}