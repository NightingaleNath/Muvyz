package com.codelytical.muvyz.cast.presentation.castdetails

sealed interface CastDetailsEvents {
    data object NavigateBack : CastDetailsEvents
}
