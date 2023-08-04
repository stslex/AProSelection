package com.stslex.aproselection.core.user.di

import com.stslex.aproselection.core.user.data.repository.UserRepository
import com.stslex.aproselection.core.user.data.repository.UserRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moduleCoreUser = module {
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
}