package com.jasson.conforta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasson.conforta.dummy.DummyData
import com.jasson.conforta.local.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true, errorMessages = emptyList()
                )
            }
            try {
                val fetchedList = repository.getGridItems()

                _uiState.update { currentState ->
                    currentState.copy(
                        list = fetchedList, isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        errorMessages = currentState.errorMessages
                                + "Falha ao carregar itens: ${e.localizedMessage}",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun loadSubGridItems(subItemIndex: Int) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    errorMessages = emptyList(),
                    subList = currentState.list[subItemIndex].subGridItems
                )
            }
        }
    }

    fun loadDetail(detailIndex: Int= 0) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    errorMessages = emptyList(),
                    detail = currentState.subList[detailIndex].subDetail
                )
            }
        }
    }
}

data class UiState(
    val list: List<DummyData.GridItem> = emptyList(),
    val subList: List<DummyData.SubGridItem> = emptyList(),
    val detail: DummyData.Detail? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList()
)