package com.stslex.aproselection.di.core

import android.content.Context
import com.stslex.aproselection.core.core.AppCoreApi
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppCoreComponent : AppCoreApi {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppCoreApi
    }
}