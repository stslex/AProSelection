package com.stslex.aproselection.core.user.di

import com.stslex.aproselection.core.network.di.NetworkApi
import dagger.Component

@Component(
    modules = [UserCoreModule::class],
    dependencies = [UserCoreDependencies::class]
)
interface UserCoreComponent : UserCoreApi {

    @Component.Factory
    interface Factory {
        fun create(dependencies: UserCoreDependencies): UserCoreComponent
    }

    @Component(dependencies = [NetworkApi::class])
    interface UserCoreComponentDependencies : UserCoreDependencies {

        @Component.Factory
        interface Factory {

            fun create(
                networkApi: NetworkApi
            ): UserCoreDependencies
        }
    }
}