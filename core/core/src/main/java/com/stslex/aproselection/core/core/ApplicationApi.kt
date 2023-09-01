package com.stslex.aproselection.core.core

import android.content.Context

interface ApplicationApi {

    val appApi: AppApi
}

val Context.appApi: AppApi
    get() = (this as ApplicationApi).appApi