package com.stslex.aproselection.feature.main.di

import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.navigation.di.NavigationApi
import com.stslex.aproselection.feature.main.MainActivity
import dagger.Component

@Component(
    modules = [MainModule::class],
    dependencies = [MainDependencies::class]
)
interface MainComponent {

    @Component.Factory
    interface Factory {
        fun create(dependencies: MainDependencies): MainComponent
    }

    @Component(dependencies = [AppApi::class, NavigationApi::class])
    interface MainDependenciesComponent : MainDependencies {

        @Component.Factory
        interface Factory {
            fun create(
                appApi: AppApi,
                navigationApi: NavigationApi
            ): MainDependenciesComponent
        }
    }

    fun inject(activity: MainActivity)
}

