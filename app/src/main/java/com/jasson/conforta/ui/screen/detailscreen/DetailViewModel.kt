package com.jasson.conforta.ui.screen.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasson.conforta.domain.model.DetalheSubcategoria
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val detail: DetalheSubcategoria? = null,
)

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadDetail(detailIndex: Int = 0) {
        viewModelScope.launch {
            // TODO carregar detalhes
        }
    }
}