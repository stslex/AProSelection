package com.stslex.aproselection.core.ui.navigation

sealed class AppArguments {

    abstract val arguments: List<String>

    open val argumentsForRoute: String
        get() = arguments.joinToString(separator = "/", prefix = "/")

    object Empty : AppArguments() {
        override val arguments: List<String>
            get() = emptyList()
        override val argumentsForRoute: String
            get() = String()
    }
}