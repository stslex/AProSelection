package com.stslex.aproselection.feature.home.ui

import com.stslex.aproselection.core.ui.base.BaseViewModel
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore.Action
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore.Event
import com.stslex.aproselection.feature.home.ui.store.HomeScreenStore.State

class HomeViewModel(
    private val store: HomeScreenStore,
) : BaseViewModel<State, Event, Action>(store)