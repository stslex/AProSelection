package com.stslex.aproselection.core.user.di

import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.network.di.NetworkApiBuilder

object UserCoreApiBuilder {

    fun build(appApi: AppApi): UserCoreApi = DaggerUserCoreComponent
        .factory()
        .create(
            dependencies = DaggerUserCoreComponent_UserCoreComponentDependencies
                .factory()
                .create(
                    networkApi = NetworkApiBuilder.build(appApi)
                )
        )
}