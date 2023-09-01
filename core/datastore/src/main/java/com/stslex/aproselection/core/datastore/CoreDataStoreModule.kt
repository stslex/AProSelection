package com.stslex.aproselection.core.datastore

import com.stslex.aproselection.core.datastore.store.AppDataStore
import com.stslex.aproselection.core.datastore.store.AppDataStoreImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreDataStoreModule = module {
    singleOf(::AppDataStoreImpl) { bind<AppDataStore>() }
}