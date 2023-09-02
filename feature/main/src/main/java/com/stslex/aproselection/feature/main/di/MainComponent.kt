package com.stslex.aproselection.feature.main.di

import androidx.lifecycle.ViewModelProvider
import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.ui.di.NavigationApi
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

    val factory: ViewModelProvider.Factory
}

