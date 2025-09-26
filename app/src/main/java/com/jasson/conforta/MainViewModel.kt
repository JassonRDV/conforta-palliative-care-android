package com.jasson.conforta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasson.conforta.domain.model.CuidadoPaliativoAgrupado
import com.jasson.conforta.data.usecase.GetPalliativeCareDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainUiState(
    val cuidadosAgrupados: List<CuidadoPaliativoAgrupado> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList()
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPalliativeCareDataUseCase: GetPalliativeCareDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadPalliativeCareData()
    }

    fun loadPalliativeCareData(fileId: String = DEFAULT_PALLIATIVE_CARE_FILE_ID) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true, errorMessages = emptyList())
            }

            val result = getPalliativeCareDataUseCase(fileId = fileId)

            result.fold(
                onSuccess = { dadosAgrupados ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            cuidadosAgrupados = dadosAgrupados,
                            isLoading = false
                        )
                    }
                },
                onFailure = { exception ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            errorMessages = listOf(
                                exception.message
                                    ?: "Ocorreu um erro desconhecido ao carregar dados"
                            )
                        )
                    }
                }
            )
        }
    }

    companion object {
        private const val DEFAULT_PALLIATIVE_CARE_FILE_ID = "1xh2b-SADBn2x2wvUp10P9nriIKkyYVo8"
    }
}

