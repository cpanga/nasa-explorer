package com.example.nasaexplorer

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nasaexplorer.ui.theme.NasaExplorerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import com.example.nasaexplorer.BuildConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            NasaExplorerTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->
                    MainContent(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        scope = scope,
                        snackbarHostState = snackbarHostState
                    )
                }
            }
        }
    }
}

@Composable
fun MainContent(
    name: String,
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    Column(
        modifier
            .padding(all = 12.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.width(20.dp))
        Row(modifier.padding(all = 8.dp), horizontalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painter = painterResource(R.drawable.nasa_meatball),
                contentDescription = "Nasa Round Logo",
                modifier = Modifier.size(200.dp)
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        MainButton(
            onClick = {
                Timber.i("Button 1 Clicked")
                scope.launch { snackbarHostState.showSnackbar("Button Clicked") }
            },
            text = "First Navigation"
        )
    }
}

@Composable
fun MainButton(onClick: () -> Unit, text: String) {
    ElevatedButton(onClick = { onClick() }) {
        Text(text)
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NasaExplorerTheme {
        MainContent("Bello",
            modifier = Modifier,
            scope = rememberCoroutineScope(),
            snackbarHostState = remember { SnackbarHostState() })
    }
}