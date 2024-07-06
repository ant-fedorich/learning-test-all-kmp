package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SampleBox() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(Color.Yellow)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Hello World")
    }
}