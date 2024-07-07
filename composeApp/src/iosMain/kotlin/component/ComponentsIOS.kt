package component

import MyButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIButton
import platform.UIKit.UIButtonTypePlain
import platform.UIKit.UIControlEventTouchUpInside
import platform.UIKit.UIControlStateNormal
import platform.UIKit.UIViewController


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


@Composable
fun IOSButton() {
    MyButton("Hello", {})
}

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