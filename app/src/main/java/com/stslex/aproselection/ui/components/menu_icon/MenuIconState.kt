package com.stslex.aproselection.ui.components.menu_icon

enum class MenuIconState {
    OPEN, CLOSE;

    fun inverse(): MenuIconState = when (this) {
        OPEN -> CLOSE
        CLOSE -> OPEN
    }
}