package com.stslex.aproselection.controller

import kotlinx.coroutines.flow.StateFlow

interface AuthController {

    val isAuth: StateFlow<Boolean?>

    fun init()
}

