package com.stslex.aproselection.feature.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.stslex.aproselection.core.ui.theme.AppDimens
import com.stslex.aproselection.feature.home.ui.model.UserUi
import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KProperty0

@Composable
fun HomeScreen(
    logOut: () -> Unit,
    users: KProperty0<StateFlow<PagingData<UserUi>>>,
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
                SelectUsersList(
                    users = lazyPagingItems
                )
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

@Composable
fun SelectUsersList(
    users: LazyPagingItems<UserUi>,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxSize()
            .padding(
                vertical = AppDimens.Padding.large,
                horizontal = AppDimens.Padding.medium
            )
    ) {
        LazyColumn(
        ) {
            items(
                count = users.itemCount,
                contentType = users.itemContentType { "user" },
                key = users.itemKey { it.uuid }
            ) { index ->
                val user = users[index]
                val userTitle = user?.let {
                    it.nickname.ifBlank { it.username }
                }.orEmpty()
                Row {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(AppDimens.Padding.big))
                    Text(
                        text = userTitle
                    )
                }
            }
        }
    }

}