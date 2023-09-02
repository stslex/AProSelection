package com.stslex.aproselection.feature.auth.di

import com.stslex.aproselection.core.datastore.store.AppDataStore
import com.stslex.aproselection.core.navigation.navigator.Navigator
import com.stslex.aproselection.core.network.clients.auth.AuthNetworkClient

interface AuthDependencies {

    val authNetworkClient: AuthNetworkClient

    val dataStore: AppDataStore

    val navigator: Navigator
}