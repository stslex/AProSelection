package com.stslex.aproselection.core.network.di

import com.stslex.aproselection.core.network.client.NetworkClient
import com.stslex.aproselection.core.network.client.NetworkClientImpl
import com.stslex.aproselection.core.network.clients.auth.AuthNetworkClient
import com.stslex.aproselection.core.network.clients.auth.AuthNetworkClientImpl
import com.stslex.aproselection.core.network.clients.user.UserNetworkClient
import com.stslex.aproselection.core.network.clients.user.UserNetworkClientImpl
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {

    @Binds
    fun bindNetworkClient(impl: NetworkClientImpl): NetworkClient

    @Binds
    fun bindsAuthNetworkClient(impl: AuthNetworkClientImpl): AuthNetworkClient

    @Binds
    fun bindsUserNetworkClient(impl: UserNetworkClientImpl): UserNetworkClient
}
