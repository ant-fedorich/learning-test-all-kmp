import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIViewController

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




