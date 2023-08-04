package com.stslex.aproselection.feature.home.domain

import com.stslex.aproselection.feature.home.domain.model.UserDomain

interface HomeInteractor {

    suspend fun getAllUsers(page: Int, pageSize: Int): List<UserDomain>

    suspend fun logOut()
}