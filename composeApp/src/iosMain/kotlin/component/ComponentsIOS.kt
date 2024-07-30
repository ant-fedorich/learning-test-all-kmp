package component

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.window.ComposeUIViewController
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


@OptIn(ExperimentalForeignApi::class)
fun createAlertDialogForIOS(title: String, message: String): UIViewController {
    return ComposeUIViewController {
        // Your Compose content for the dialog goes here
        AlertDialog(
            onDismissRequest = {}, // Dismiss logic (e.g., navigate back)
            title = { Text(title) },
            text = { Text(message) },
            confirmButton = {
                Button(onClick = { /* Action */ }) {
                    Text("OK")
                }
            }
        )
    }
}

@OptIn(ExperimentalForeignApi::class)
fun uiAlertController(): UIAlertController {
    val alertController = UIAlertController()
    alertController.message = "Alert"
    alertController.title = "This is an alert dialog with Compose content."

// Set preferredStyle using Objective-C runtime
    val preferredStyleKey = NSStringFromSelector(sel_registerName("preferredStyle"))
    objc_setAssociatedObject(
        alertController,
        preferredStyleKey.utf8,
        NSNumber.numberWithLong(UIAlertControllerStyleAlert.toLong()),
        OBJC_ASSOCIATION_RETAIN_NONATOMIC
    )
    return alertController
}

// Usage example
//val alertController = UIAlertController(
//    title = "Alert",
//    message = "This is an alert dialog with Compose content.",
//    preferredStyle = UIAlertControllerStyleAlert
//)
//
//val name = "Hello"
//
//
//fun test() {
//    // Add Compose content to the dialog
//    val composeViewController = createAlertDialogForIOS("Alert", "This is an alert dialog with Compose content.")
//    alertController.setValue(composeViewController, forKey: "contentViewController")
//
//// Present the alert (from your iOS code)
//    presentViewController(alertController, animated: true, completionHandler: null)
//}



//@Composable
//fun MyButton(onClick: () -> Unit, text: String) {
//    Platform.osFamily.name {
//        Button(
//            onClick = onClick,
//            modifier = Modifier.padding(8.dp)
//        ) {
//            Text(text)
//        }
//    }
//}


//// iosMain module
//@Composable
//fun PlatformButton(text: String, onClick: () -> Unit) {
//    UIKitButton(
//        text = text,
//        onClick = onClick
//    )
//}


//// iosMain module
//@OptIn(ExperimentalForeignApi::class)
//@Composable
//fun UIKitButton(text: String, onClick: () -> Unit) {
//    val viewController = remember {
//        val button = UIButton()
//
//        button.setTitle(text, forState = UIControlStateNormal)
//        button.addTarget(
//            action = null,
//            forControlEvents = UIControlEventTouchUpInside,
//            target = null
//        )
//
//        val viewController = UIViewController()
//        viewController.view.addSubview(button)
//
//        viewController
//    }
//
//    // Use UIKit to wrap the UIButton into a Compose view
//    UIKitComposeView(viewController)
//}

//@Composable
//fun UIKitComposeView(viewController: UIViewController) {
//    // This function bridges UIKit views into Compose
//    ComposeUIViewControllerBridge(viewController)
//}

//@Composable
//fun ComposeUIViewControllerBridge(viewController: UIViewController) {
//    UIKitView(view = {
//        viewController.view
//    })
//}