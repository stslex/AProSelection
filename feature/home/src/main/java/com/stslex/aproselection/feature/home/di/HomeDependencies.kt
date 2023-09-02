package com.stslex.aproselection.feature.home.di

import com.stslex.aproselection.core.datastore.store.AppDataStore
import com.stslex.aproselection.core.ui.di.Navigator
import com.stslex.aproselection.core.user.data.repository.UserRepository

interface HomeDependencies {

    val userRepository: UserRepository

    val appDataStore: AppDataStore

    val navigator: Navigator
}