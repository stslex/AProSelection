package com.stslex.aproselection.feature.main.di

import com.stslex.aproselection.core.core.AuthController
import com.stslex.aproselection.core.ui.di.Navigator

interface MainDependencies {

    val controller: AuthController

    val navigator: Navigator
}