package com.stslex.aproselection.feature.auth.domain.interactor

import com.stslex.aproselection.feature.auth.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthInteractorImpl(
    private val repository: AuthRepository
) : AuthInteractor {

    override val hello: Flow<String>
        get() = repository.hello
}