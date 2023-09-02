package com.stslex.aproselection.core.user.di

import com.stslex.aproselection.core.user.data.repository.UserRepository
import com.stslex.aproselection.core.user.data.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface UserCoreModule {

    @Binds
    fun bindsUserRepository(impl: UserRepositoryImpl): UserRepository
}