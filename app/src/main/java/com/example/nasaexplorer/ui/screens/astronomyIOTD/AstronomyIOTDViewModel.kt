package com.example.nasaexplorer.ui.screens.astronomyIOTD

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaexplorer.BuildConfig
import com.example.nasaexplorer.logic.AstronomyImageResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


data class AstronomyIOTDUiState(
    val isLoading: Boolean = false,
    var astronomyIOTD: AstronomyImageResponse? = null,
)

@HiltViewModel
class AstronomyIOTDViewModel @Inject constructor(
    private val astronomyIOTDRepository: AstronomyIOTDRepository
): ViewModel() {

    private val apiKey = BuildConfig.NASA_API_KEY
    private val _uiState = MutableStateFlow(AstronomyIOTDUiState())
    val uiState: StateFlow<AstronomyIOTDUiState> = _uiState.asStateFlow()

    init {
        Timber.i("astr API Key: $apiKey")
        fetchAstronomyIOTD(apiKey)
    }

    //TODO: consult jetnews example to see how result is formed properly
    private fun fetchAstronomyIOTD(apiKey: String) {
        Timber.i("FetchAstronomyIOTD called")
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            var astronomyImage: AstronomyImageResponse? = null
            try {
                astronomyImage = astronomyIOTDRepository.getAstronomyImage(apiKey)
                Timber.i("Astronomy image fetched: $astronomyImage")
                Timber.i("Name of the image: ${astronomyImage.title}")

            } catch (e: Exception) {
                Timber.e("Error fetching astronomy image: ${e.message}")
            }
            _uiState.update {
                it.copy(isLoading = false, astronomyIOTD = astronomyImage)
            }
        }
    }
}