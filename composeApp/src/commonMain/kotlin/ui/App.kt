package ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import ui.screen.MainScreen


@Composable
fun App() {
    MaterialTheme {
        MainScreen({})
    }
}
