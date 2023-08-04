package com.stslex.aproselection.core.network.di

import com.stslex.aproselection.core.network.client.NetworkClient
import com.stslex.aproselection.core.network.client.NetworkClientImpl
import com.stslex.aproselection.core.network.clients.auth.AuthNetworkClient
import com.stslex.aproselection.core.network.clients.auth.AuthNetworkClientImpl
import com.stslex.aproselection.core.network.clients.user.UserNetworkClient
import com.stslex.aproselection.core.network.clients.user.UserNetworkClientImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ModuleCoreNetwork {

    val moduleCoreNetwork = module {
        singleOf(::NetworkClientImpl) { bind<NetworkClient>() }
        singleOf(::AuthNetworkClientImpl) { bind<AuthNetworkClient>() }
        singleOf(::UserNetworkClientImpl) { bind<UserNetworkClient>() }
    }
}