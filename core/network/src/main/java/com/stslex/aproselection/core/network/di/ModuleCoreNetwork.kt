package com.stslex.aproselection.core.network.di

import com.stslex.aproselection.core.network.client.NetworkClient
import com.stslex.aproselection.core.network.client.NetworkClientImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object ModuleCoreNetwork {

    val moduleCoreNetwork = module {
        singleOf(::NetworkClientImpl) { bind<NetworkClient>() }
    }
}