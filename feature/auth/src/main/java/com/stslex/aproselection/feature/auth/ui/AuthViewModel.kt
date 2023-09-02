package com.stslex.aproselection.feature.auth.ui

import com.stslex.aproselection.core.navigation.destination.NavigationScreen
import com.stslex.aproselection.core.navigation.navigator.Navigator
import com.stslex.aproselection.core.ui.base.BaseViewModel
import com.stslex.aproselection.feature.auth.ui.store.AuthStore
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.Action
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.Event
import com.stslex.aproselection.feature.auth.ui.store.AuthStore.State
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    store: AuthStore,
    private val navigator: Navigator
) : BaseViewModel<State, Event, Action>(store) {

    fun processNavigation(event: Event.Navigation) {
        when (event) {
            Event.Navigation.HomeFeature -> navigator.navigate(NavigationScreen.Home)
        }
    }
}
