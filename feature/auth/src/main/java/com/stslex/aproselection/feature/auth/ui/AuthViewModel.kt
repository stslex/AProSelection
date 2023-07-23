package com.stslex.aproselection.feature.auth.ui

import com.stslex.aproselection.core.ui.base.BaseViewModel
import com.stslex.aproselection.feature.auth.domain.interactor.AuthInteractor
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch

class AuthViewModel(
    private val interactor: AuthInteractor
) : BaseViewModel() {

    val text: StateFlow<String>
        get() = interactor
            .hello
            .catch { throwable ->
                handleError(throwable)
            }
            .stateIn("")
}
