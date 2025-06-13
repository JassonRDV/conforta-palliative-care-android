package com.jasson.conforta.ui.screen.subscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasson.conforta.domain.model.DetalheSubcategoria
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SubUiState(
    val subGridItems: List<DetalheSubcategoria> = emptyList(),
)

@HiltViewModel
class SubGridViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SubUiState())
    val uiState: StateFlow<SubUiState> = _uiState.asStateFlow()

    fun loadSubGridItems(subItemIndex: Int) {
        viewModelScope.launch {
            // TODO carregar dados do subgrid
        }
    }
}