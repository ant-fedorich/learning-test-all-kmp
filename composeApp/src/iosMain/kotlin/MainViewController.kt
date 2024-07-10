import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitViewController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIViewController

fun MainViewController() = ComposeUIViewController {
    MaterialTheme {
//        MainScreen()
//        MainScreenShared()
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.size(50.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
            ) {
                Text("Click me please")
            }
        }
    }
}


@OptIn(ExperimentalForeignApi::class)
fun ComposeEntryPointWithUIViewController(
    uiViewController: () -> UIViewController
) = ComposeUIViewController {
    Column(
        Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
    ) {
        Text("How to use SwiftUI inside Compose Multiplatform")
        UIKitViewController(
            modifier = Modifier.size(800.dp).padding(16.dp),
            factory = uiViewController,
        )
    }
}




