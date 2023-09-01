package com.stslex.aproselection.di

import com.stslex.aproselection.SelectApplication
import com.stslex.aproselection.core.core.AppApi
import com.stslex.aproselection.core.datastore.di.AppDataStoreApi
import com.stslex.aproselection.core.core.AppCoreApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class],
    dependencies = [
        AppCoreApi::class,
        AppDataStoreApi::class
    ]
)
interface AppComponent : AppApi {

    @Component.Factory
    interface Factory {

        fun create(
            appApi: AppCoreApi,
            dataStoreApi: AppDataStoreApi
        ): AppComponent
    }

    fun inject(application: SelectApplication)
}
