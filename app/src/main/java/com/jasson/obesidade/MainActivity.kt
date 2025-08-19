package com.jasson.obesidade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jasson.obesidade.ui.theme.ObesidadeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.uiState.value.isLoading &&
                        mainViewModel.uiState.value.errorMessages.isEmpty()
            }
        }

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ObesidadeTheme {
                AppNavigator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ObesidadeTheme {
        AppNavigator()
    }
}