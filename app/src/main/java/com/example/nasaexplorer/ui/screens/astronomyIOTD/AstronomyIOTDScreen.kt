package com.example.nasaexplorer.ui.screens.astronomyIOTD

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nasaexplorer.ui.components.LoadImageFromUrl
import com.example.nasaexplorer.ui.components.LoadingSpinner
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

@Composable
fun AstronomyIOTDScreen(
    modifier: Modifier = Modifier,
    viewModel: AstronomyIOTDViewModel = hiltViewModel(),
    name: String,
    scope: CoroutineScope,
) {
    val data = viewModel.uiState.collectAsState()
    Column(
        modifier
            .padding(all = 12.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val astronomyIOTD = data.value.astronomyIOTD
        val url = astronomyIOTD?.hdurl ?: astronomyIOTD?.url
        val isLoading = data.value.isLoading
        Timber.i("astronomyIOTD: $astronomyIOTD")
        Text(
            text = astronomyIOTD?.title ?: "NO DATA" ,
            modifier = modifier,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.labelLarge
        )
        LoadImageFromUrl(url ?: "")
        // Change to use state
        LoadingSpinner(isLoading)
    }
}


