package com.example.nasaexplorer.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nasaexplorer.R
import com.example.nasaexplorer.ui.theme.NasaExplorerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun LandingScreen(
    name: String,
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    navigateToAOTD: () -> Unit,
) {
    Column(
        modifier
            .padding(all = 12.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Row(modifier.padding(all = 8.dp), horizontalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.width(20.dp))
            Image(
                painter = painterResource(R.drawable.nasa_meatball),
                contentDescription = "Nasa Round Logo",
                modifier = Modifier.size(200.dp)
            )
        }
        Spacer(modifier = Modifier.width(120.dp))
        MainButton(
            onClick = {
                Timber.i("Button 1 Clicked")
                navigateToAOTD()
            },
            text = "Astronomy Image of the Day",
            image = painterResource(R.drawable.telescope),
            contDesc = "Telescope Icon"
        )
        Spacer(modifier = Modifier.width(120.dp))
        MainButton(
            onClick = {
                Timber.i("Button 2 Clicked")
                scope.launch { snackbarHostState.showSnackbar("Button 2 Clicked") }
            }, text = "Second Navigation", image = null, contDesc = null
        )
        Spacer(modifier = Modifier.width(120.dp))
        MainButton(
            onClick = {
                Timber.i("Button 3 Clicked")
                scope.launch { snackbarHostState.showSnackbar("Button 3 Clicked") }
            }, text = "Third Navigation", image = null, contDesc = null
        )
    }
}

@Composable
fun MainButton(onClick: () -> Unit, text: String, image: Painter?, contDesc: String?) {
    ElevatedButton(
        modifier = Modifier
            .widthIn(min = 200.dp)
            .height(60.dp)
            .padding(all = 8.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.purple_500), contentColor = Color.White
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (image != null && contDesc != null) {
                Icon(image, contDesc)
            }
            Text(text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LandingPreview() {
    NasaExplorerTheme {
        LandingScreen("Bello",
            modifier = Modifier,
            scope = rememberCoroutineScope(),
            snackbarHostState = remember { SnackbarHostState() },
            navigateToAOTD = {})
    }
}