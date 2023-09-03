package com.stslex.aproselection.core.datastore.di

import com.stslex.aproselection.core.datastore.store.AppDataStore

interface AppDataStoreApi {

    val dataStore: AppDataStore
}