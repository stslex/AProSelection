package com.stslex.aproselection.ui.components.menu_icon

enum class AppDrawerState {
    OPEN, CLOSE;

    fun inverse(): AppDrawerState = when (this) {
        OPEN -> CLOSE
        CLOSE -> OPEN
    }
}