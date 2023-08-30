package com.stslex.aproselection.feature.home.ui.store

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import com.stslex.aproselection.core.ui.base.store.Store
import com.stslex.aproselection.feature.home.ui.model.UserUi
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore.Action
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore.Event
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore.State
import kotlinx.coroutines.flow.StateFlow

interface HomeScreenStore : Store<State, Event, Action> {

    @Stable
    data class State(
        val users: () -> StateFlow<PagingData<UserUi>>
    ) : Store.State

    @Stable
    sealed interface Action : Store.Action {

        data object LogOut : Action
    }

    @Stable
    sealed interface Event : Store.Event
}