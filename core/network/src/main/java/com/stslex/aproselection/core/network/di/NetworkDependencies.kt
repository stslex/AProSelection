package com.stslex.aproselection.core.network.di

import com.stslex.aproselection.core.datastore.store.AppDataStore

interface NetworkDependencies {

    val dataStore: AppDataStore
}