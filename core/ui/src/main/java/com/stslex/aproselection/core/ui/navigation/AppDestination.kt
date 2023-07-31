package com.stslex.aproselection.core.ui.navigation

enum class AppDestination(
    vararg val argsNames: String
) {
    AUTH,
    HOME;

    val route: String
        get() = StringBuilder()
            .append(name, SEPARATOR_ROUTE, TAG_ROUTE)
            .toString()
            .lowercase()

    val navigationRoute: String
        get() = "$route${argsNames.argumentsRoute}"

    private val Array<out String>.argumentsRoute: String
        get() = if (isEmpty()) {
            String()
        } else {
            joinToString(separator = "}/{", prefix = "/{", postfix = "}")
        }

    companion object {
        private const val SEPARATOR_ROUTE = "_"
        private const val TAG_ROUTE = "route"
    }
}