package org.test.testkmp

import MainScreenShared
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import getPlatform
import ui.screen.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
           MainScreenShared()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    MainScreenShared()
}






