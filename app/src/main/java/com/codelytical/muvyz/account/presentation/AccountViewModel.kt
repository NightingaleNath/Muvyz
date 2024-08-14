package com.codelytical.muvyz.account.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelytical.muvyz.core.domain.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    val theme = preferenceRepository.getTheme()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0,
        )
    fun updateTheme(themeValue: Int) {
        viewModelScope.launch {
            preferenceRepository.saveTheme(themeValue)
        }
    }
}
