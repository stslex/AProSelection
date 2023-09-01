package com.stslex.aproselection.core.datastore.di

import com.stslex.aproselection.core.datastore.store.AppDataStore
import com.stslex.aproselection.core.datastore.store.AppDataStoreImpl
import dagger.Binds
import dagger.Module

@Module
interface AppDataStoreModule {

    @Binds
    fun bindDataStore(impl: AppDataStoreImpl): AppDataStore
}