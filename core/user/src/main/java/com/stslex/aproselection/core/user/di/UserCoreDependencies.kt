package com.stslex.aproselection.core.user.di

import com.stslex.aproselection.core.network.clients.user.UserNetworkClient

interface UserCoreDependencies {

    val networkClient: UserNetworkClient
}