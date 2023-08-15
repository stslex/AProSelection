package com.stslex.aproselection.feature.home.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.stslex.aproselection.core.ui.base.BasePager.makePager
import com.stslex.aproselection.core.ui.base.BaseViewModel
import com.stslex.aproselection.feature.home.domain.HomeInteractor
import com.stslex.aproselection.feature.home.ui.model.UserUi
import com.stslex.aproselection.feature.home.ui.model.toPresentation
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val interactor: HomeInteractor
) : BaseViewModel() {

    val users: StateFlow<PagingData<UserUi>>
        get() = makePager(interactor::getAllUsers)
            .mapState { user -> user.toPresentation() }

    fun logOut() {
        viewModelScope.launch {
            interactor.logOut()
        }
    }
}