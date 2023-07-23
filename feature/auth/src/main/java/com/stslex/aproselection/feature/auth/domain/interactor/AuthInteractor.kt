package com.stslex.aproselection.feature.auth.domain.interactor

import kotlinx.coroutines.flow.Flow

interface AuthInteractor {

    val hello: Flow<String>
}