package com.example.nasaexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.nasaexplorer.ui.components.TopBar
import com.example.nasaexplorer.ui.theme.NasaExplorerTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            NasaExplorerTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                // Set up navigation
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val topBarVisible = remember { mutableStateOf(true) }
                topBarVisible.value = navBackStackEntry?.destination?.route != Landing.route
                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    topBar = {
                        TopBar(screenName = "Nasa Explorer", topBarVisible = topBarVisible) {
                            Timber.i("Back button clicked")
                            navController.navigateUp()
                        }
                    }) { innerPadding ->
                    // Create a NavHost and define the screens
                    NavHost(
                        navController = navController,
                        startDestination = Landing.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = Landing.route) {
                            Landing.screen(
                                scope, snackbarHostState
                            ) { navController.navigateSingleTopTo(AstronomyIOTD.route) }
                        }
                        composable(route = AstronomyIOTD.route) {
                            AstronomyIOTD.screen(scope, snackbarHostState) {}
                        }
                    }
                }
            }
        }
    }
}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

