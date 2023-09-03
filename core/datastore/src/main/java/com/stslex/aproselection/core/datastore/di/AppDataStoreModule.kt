package com.stslex.aproselection.core.datastore.di

import com.stslex.aproselection.core.datastore.store.AppDataStore
import com.stslex.aproselection.core.datastore.store.AppDataStoreImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppDataStoreModule {

    @Binds
    @Singleton
    fun bindDataStore(impl: AppDataStoreImpl): AppDataStore
}