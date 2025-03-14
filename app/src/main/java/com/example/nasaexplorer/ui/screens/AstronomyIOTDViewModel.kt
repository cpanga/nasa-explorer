package com.example.nasaexplorer.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaexplorer.logic.AstronomyIOTD
import com.example.nasaexplorer.logic.getAOTD
import com.example.nasaexplorer.logic.successOr
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class AstronomyIOTDUiState(
    val isLoading: Boolean = false,
    var astronomyIOTD: AstronomyIOTD? = null,
)

class AstronomyIOTDViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(AstronomyIOTDUiState())
    val uiState: StateFlow<AstronomyIOTDUiState> = _uiState.asStateFlow()

    init {
        refreshAll()
    }

    /**
     * Refresh all data
     */
    private fun refreshAll() {
        // TODO: Implement database
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val astronomyIOTDDeferred = async { getAOTD() }

            val astronomyIOTD = astronomyIOTDDeferred.await().successOr(null)
            _uiState.update {
                it.copy(isLoading = false, astronomyIOTD = astronomyIOTD)
            }
        }
    }

}

///**
// * Factory for InterestsViewModel that takes PostsRepository as a dependency
// */
//companion object {
//    fun provideFactory(
//        interestsRepository: InterestsRepository,
//    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return InterestsViewModel(interestsRepository) as T
//        }
//    }
//}
//}