package com.example.nasaexplorer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nasaexplorer.ui.components.LoadingSpinner
import kotlinx.coroutines.CoroutineScope

@Composable
fun AstronomyIOTDScreen(
    name: String,
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
) {
    Column(
        modifier
            .padding(all = 12.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val isLoading: MutableState<Boolean> = remember { mutableStateOf(true) }
        Text(
            text = "Hello $name!",
            modifier = modifier,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyMedium
        )
        LoadingSpinner(isLoading)
    }
}
