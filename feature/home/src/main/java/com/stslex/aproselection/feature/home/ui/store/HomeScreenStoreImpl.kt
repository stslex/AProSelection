package com.stslex.aproselection.feature.home.ui.store

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.stslex.aproselection.core.ui.base.BasePager
import com.stslex.aproselection.core.ui.base.store.BaseStoreImpl
import com.stslex.aproselection.feature.home.domain.HomeInteractor
import com.stslex.aproselection.feature.home.ui.model.toPresentation
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore.Action
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore.Event
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeScreenStoreImpl(
    private val interactor: HomeInteractor,
) : HomeScreenStore, BaseStoreImpl<State, Event, Action>() {

    override val initialState: State = State(
        users = { users }
    )

    override val state: MutableStateFlow<State> = MutableStateFlow(initialState)

    private val users
        get() = BasePager
            .makePager(interactor::getAllUsers)
            .flow
            .map { pagingData ->
                pagingData.map { user ->
                    user.toPresentation()
                }
            }
            .flowOn(Dispatchers.IO)
            .cachedIn(scope)
            .stateIn(
                scope = scope,
                started = SharingStarted.Lazily,
                initialValue = PagingData.empty()
            )

    override fun processAction(action: Action) {
        when (action) {
            Action.LogOut -> onLogOutAction()
        }
    }

    private fun onLogOutAction() {
        scope.launch(Dispatchers.IO) {
            interactor.logOut()
        }
    }
}