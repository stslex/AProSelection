package com.stslex.aproselection.feature.home.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.stslex.aproselection.core.ui.base.BasePagingSource
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
        get() = Pager(config = config) {
            BasePagingSource(interactor::getAllUsers)
        }
            .mapState { user ->
                user.toPresentation()
            }

    fun logOut() {
        viewModelScope.launch {
            interactor.logOut()
        }
    }

    companion object {

        private val config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        )
    }
}