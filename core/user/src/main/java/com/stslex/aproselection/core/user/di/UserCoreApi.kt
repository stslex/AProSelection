package com.stslex.aproselection.core.user.di

import com.stslex.aproselection.core.user.data.repository.UserRepository

interface UserCoreApi {

    val userRepository: UserRepository
}