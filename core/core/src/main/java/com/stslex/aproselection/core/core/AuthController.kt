package com.stslex.aproselection.core.core

import kotlinx.coroutines.flow.StateFlow

interface AuthController {

    val isAuth: StateFlow<Boolean?>

    suspend fun init()
}

