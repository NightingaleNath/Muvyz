package com.codelytical.muvyz.account.presentation

sealed interface AccountScreenUiEvents {
    data object OnShareClicked : AccountScreenUiEvents
    data object ShowThemesDialog: AccountScreenUiEvents
}
