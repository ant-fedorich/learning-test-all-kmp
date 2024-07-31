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
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitViewController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import component.createAlertDialogForIOS
import component.uiAlertController
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.utf8
import platform.Foundation.NSNumber
import platform.Foundation.NSStringFromSelector
import platform.Foundation.numberWithLong
import platform.Foundation.setValue
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIViewController
import platform.objc.OBJC_ASSOCIATION_RETAIN_NONATOMIC
import platform.objc.objc_setAssociatedObject
import platform.objc.sel_registerName

/*@OptIn(ExperimentalForeignApi::class)
fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        MaterialTheme {
            var showAlert by remember { mutableStateOf(false) } // State to control the alert

            Column {
//                Text("Title for IOS")

                Button(onClick = { showAlert = true }) { // Button to trigger the alert
                    Text("Show Alert!!!")
                }

                if (showAlert) { // Show the alert when state is true
                    AlertDialog(
                        onDismissRequest = { showAlert = false }, // Dismiss the alert
                        title = { Text("Alert") },
                        text = { Text("This is an alert dialog with Compose content.") },
                        confirmButton = {
                            Button(onClick = { showAlert = false }) { // Dismiss on "OK" click
                                Text("OK")
                            }
                        }
                    )
                }
            }
        }
    }
}*/



//
//@OptIn(ExperimentalForeignApi::class)
//fun MainViewController(): UIViewController {
//    return ComposeUIViewController {
//        MaterialTheme {
//            var showAlert by remember { mutableStateOf(false) } // State to control the alert
//
//            Column {
////                Text("Title for IOS")
//                Button(onClick = { showAlert = true }) { // Button to trigger the alert
//                    Text("Show Alert!!!")
//                }
//            }
//        }
//    }
//}

fun createAlertDialog(title: String, message: String): UIViewController {
    return ComposeUIViewController {
        AlertDialog(
            onDismissRequest = {  }, // Dismiss the alert
            title = { Text("Alert") },
            text = { Text("This is an alert dialog with Compose content.") },
            confirmButton = {
                Button(onClick = {  }) { // Dismiss on "OK" click
                    Text("OK")
                }
            }
        )
    }
}

class ComposeAlertDialogHelper {
//    @ExportForIOS // Make this function accessible to Swift

}

//@OptIn(ExperimentalForeignApi::class)
//fun createAlertDialogForIOS(title: String, message: String): UIViewController {
//    return ComposeUIViewController {
//        // Your Compose content for the dialog goes here
//        if (showAlert) { // Show the alert when state is true
//            AlertDialog(
//                onDismissRequest = { showAlert = false }, // Dismiss the alert
//                title = { Text("Alert") },
//                text = { Text("This is an alert dialog with Compose content.") },
//                confirmButton = {
//                    Button(onClick = { showAlert = false }) { // Dismiss on "OK" click
//                        Text("OK")
//                    }
//                }
//            )
//        }
//    }
//}

/*@OptIn(ExperimentalForeignApi::class)
fun createAlertDialogForIOS(title: String, message: String): UIViewController {
    return ComposeUIViewController {
        // Your Compose content for the dialog goes here
        AlertDialog(
            onDismissRequest = {}, // Dismiss logic (e.g., navigate back)
            title = { Text(title) },
            text = { Text(message) },
            confirmButton = {
                Button(onClick = { "Action" }) {
                    Text("OK")
                }
            }
        )
    }
}*/



@OptIn(ExperimentalForeignApi::class)
@Composable
fun MainViewController() = ComposeUIViewController {
    MaterialTheme {
        Text("Title for IOS")
    }
}


//@OptIn(ExperimentalForeignApi::class)
//fun ComposeEntryPointWithUIViewController(
//    uiViewController: () -> UIViewController
//) = ComposeUIViewController {
//    Column(
//        Modifier
//            .fillMaxSize()
//            .windowInsetsPadding(WindowInsets.systemBars),
//    ) {
//        Text("How to use SwiftUI inside Compose Multiplatform")
//        UIKitViewController(
//            modifier = Modifier.size(800.dp).padding(16.dp),
//            factory = uiViewController,
//        )
//    }
//}




