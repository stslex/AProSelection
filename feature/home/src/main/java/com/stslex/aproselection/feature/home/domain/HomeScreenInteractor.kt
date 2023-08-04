package com.stslex.aproselection.feature.home.domain

import com.stslex.aproselection.feature.home.domain.model.UserDomain

interface HomeScreenInteractor {

    suspend fun getAllUsers(page: Int, pageSize: Int): List<UserDomain>
}