package com.jasson.conforta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jasson.conforta.ui.screen.mainscreen.ViewModel
import com.jasson.conforta.ui.theme.ConfortaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.uiState.value.isLoading &&
                        viewModel.uiState.value.errorMessages.isEmpty()
            }
        }

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ConfortaTheme {
                AppNavigator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConfortaTheme {
        AppNavigator()
    }
}