package com.stslex.aproselection.feature.home.domain

import com.stslex.aproselection.core.datastore.AppDataStore
import com.stslex.aproselection.core.user.data.repository.UserRepository
import com.stslex.aproselection.feature.home.domain.model.UserDomain
import com.stslex.aproselection.feature.home.domain.model.toDomain

class HomeInteractorImpl(
    private val repository: UserRepository,
    private val appDataStore: AppDataStore,
) : HomeInteractor {

    override suspend fun getAllUsers(
        page: Int,
        pageSize: Int
    ): List<UserDomain> = repository
        .getUserList(
            page = page,
            pageSize = pageSize
        )
        .map { user ->
            user.toDomain()
        }

    override suspend fun logOut() {
        appDataStore.clear()
    }
}