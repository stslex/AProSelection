package com.stslex.aproselection.core.ui.di

import android.content.Context

interface MainUiApi {

    val navigationApi: NavigationApi
}

val Context.navigationApi: NavigationApi
    get() = (this as MainUiApi).navigationApi
