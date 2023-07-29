package com.stslex.aproselection.core.datastore

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreDataStoreModule = module {
    singleOf(::AppDataStoreImpl) { bind<AppDataStore>() }
}