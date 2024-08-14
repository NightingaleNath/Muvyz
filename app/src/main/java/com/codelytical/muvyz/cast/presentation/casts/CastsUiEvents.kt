package com.codelytical.muvyz.cast.presentation.casts

import com.codelytical.muvyz.cast.domain.model.Cast

sealed interface CastsUiEvents {
    data class NavigateToCastDetails(val cast: Cast) :
        CastsUiEvents

    data object NavigateBack : CastsUiEvents
}
