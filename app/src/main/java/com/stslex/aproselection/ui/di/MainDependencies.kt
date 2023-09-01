package com.stslex.aproselection.ui.di

import com.stslex.aproselection.core.core.AuthController
import com.stslex.aproselection.core.navigation.navigator.Navigator

interface MainDependencies {

    val controller: AuthController

    val navigator: Navigator
}