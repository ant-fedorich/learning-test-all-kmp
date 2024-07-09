import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.ComposeUIViewController
import temp.IOSClass

fun MainViewController() = ComposeUIViewController {
    MaterialTheme {
        MainScreenShared()
    }

//    MyButton("Test") {
//
//    }
    getPlatform()
    IOSPlatform().name

    getPlatform().name

    IOSClass()
}




