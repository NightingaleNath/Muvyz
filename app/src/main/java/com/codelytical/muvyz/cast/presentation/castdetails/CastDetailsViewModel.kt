package com.codelytical.muvyz.cast.presentation.castdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelytical.muvyz.cast.domain.usecase.GetCastDetailsUseCase
import com.codelytical.muvyz.core.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CastDetailsViewModel @Inject constructor(
    private val getCastDetailsUseCase: GetCastDetailsUseCase,
) : ViewModel() {
    private val _castDetailsUiState = MutableStateFlow(CastDetailsUiState())
    val castDetailsUiState = _castDetailsUiState.asStateFlow()
    fun getCastDetails(id: Int) {
        viewModelScope.launch {
            _castDetailsUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            when (val result = getCastDetailsUseCase(id)) {
                is Resource.Error -> {
                    _castDetailsUiState.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Success -> {
                    _castDetailsUiState.update {
                        it.copy(
                            isLoading = false,
                            castDetails = result.data
                        )
                    }
                }
                else -> {
                    castDetailsUiState
                }
            }
        }
    }
}
