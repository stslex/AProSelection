package com.stslex.aproselection.core.network.di

import com.stslex.aproselection.core.network.client.NetworkClient
import com.stslex.aproselection.core.network.client.NetworkClientImpl
import com.stslex.aproselection.core.network.clients.auth.AuthNetworkClient
import com.stslex.aproselection.core.network.clients.auth.AuthNetworkClientImpl
import com.stslex.aproselection.core.network.clients.user.UserNetworkClient
import com.stslex.aproselection.core.network.clients.user.UserNetworkClientImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NetworkModule {

    @Binds
    @Singleton
    fun bindNetworkClient(impl: NetworkClientImpl): NetworkClient

    @Binds
    @Singleton
    fun bindsAuthNetworkClient(impl: AuthNetworkClientImpl): AuthNetworkClient

    @Binds
    @Singleton
    fun bindsUserNetworkClient(impl: UserNetworkClientImpl): UserNetworkClient
}
