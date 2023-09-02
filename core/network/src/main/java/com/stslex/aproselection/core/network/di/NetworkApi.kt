package com.stslex.aproselection.core.network.di

import com.stslex.aproselection.core.network.clients.auth.AuthNetworkClient
import com.stslex.aproselection.core.network.clients.user.UserNetworkClient

interface NetworkApi {

    val authNetworkClient: AuthNetworkClient

    val userNetworkClient: UserNetworkClient
}