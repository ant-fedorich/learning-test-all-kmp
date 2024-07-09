@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSCoder
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIAction
import platform.UIKit.UIButton
import platform.UIKit.UIButtonTypeRoundedRect
import platform.UIKit.UIButtonTypeSystem
import platform.UIKit.UIColor
import platform.UIKit.UIControlEventEditingChanged
import platform.UIKit.UIControlEventTouchUpInside
import platform.UIKit.UIControlEventValueChanged
import platform.UIKit.UIControlStateNormal
import platform.UIKit.UIDevice
import platform.UIKit.UISwitch
import platform.UIKit.UITextField
import platform.UIKit.UITextFieldViewMode
import platform.UIKit.UIView
import platform.UIKit.showsTouchWhenHighlighted
import platform.UIKit.systemBlueColor
import platform.darwin.NSObject
import kotlin.native.Platform

@OptIn(ExperimentalForeignApi::class)
@Composable
fun UIKitTextField(modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("Hello, World!") }
    UIKitView(
        factory = {
            val textField = object : UITextField(CGRectMake(0.0, 0.0, 0.0, 0.0)) {
                @ObjCAction
                fun editingChanged() {
                    message = text ?: ""
                }
            }
            textField.addTarget(
                target = textField,
                action = NSSelectorFromString(textField::editingChanged.name),
                forControlEvents = UIControlEventEditingChanged
            )
            textField.layer.borderWidth = 1.0
            textField.layer.borderColor = UIColor.blackColor().CGColor
            textField.layer.cornerRadius = 5.0


            ////
            val paddingView = UIView(CGRectMake(0.0, 0.0, 8.0, 0.0))
            textField.apply {
                leftView = paddingView
                leftViewMode = UITextFieldViewMode.UITextFieldViewModeAlways
                rightView = paddingView
                rightViewMode = UITextFieldViewMode.UITextFieldViewModeAlways
            }

            textField
        },
        modifier = modifier,
        update = { textField ->
            textField.text = message
        }
    )
}


@OptIn(ExperimentalForeignApi::class)
@Composable
fun UIKitButton(modifier: Modifier = Modifier, text: String) {
    var buttonText by remember { mutableStateOf(text) }
    var buttonClicked by remember { mutableStateOf(false) }

    UIKitView(
        factory = {
            val button = UIButton.buttonWithType(UIButtonTypeSystem).apply {
                setFrame(CGRectMake(0.0, 0.0, 200.0, 50.0))
                setTitle(buttonText, UIControlStateNormal)
                backgroundColor = UIColor.systemBlueColor
                setTitleColor(UIColor.whiteColor, UIControlStateNormal)
                isUserInteractionEnabled()// = true
//                showsTouchWhenHighlighted = true

            }

            // Create a custom class to handle the button press
            val actionHandler = object : NSObject() {
                @ObjCAction
                fun buttonPressed() {
                    buttonClicked = !buttonClicked
                    button.setTitle(if (buttonClicked) text else "$text Clicked", UIControlStateNormal)
                }
            }
            val handler = actionHandler
            button.addTarget(
                target = handler,
                action = NSSelectorFromString("buttonPressed"),
                forControlEvents = UIControlEventTouchUpInside
            )

            button
        },
        modifier = modifier.fillMaxWidth().height(50.dp),
        update = { button ->
            button.setTitle(buttonText, UIControlStateNormal)
        }
    )
}


@OptIn(ExperimentalForeignApi::class)
@Composable
fun UICheckbox(modifier: Modifier = Modifier) {
    var isChecked by remember { mutableStateOf(false) }

    UIKitView(
        factory = {
            val switch = object : UISwitch(CGRectMake(0.0, 0.0, 0.0, 0.0)) {
                @ObjCAction
                fun switchToggled() {
                    isChecked = this.isOn()
                }
            }
            switch.addTarget(
                target = switch,
                action = NSSelectorFromString(switch::switchToggled.name),
                forControlEvents = UIControlEventValueChanged
            )
            switch.setOn(true)
            switch.enabled = true
            switch
        },
        modifier = modifier.fillMaxWidth().height(30.dp),
        update = { switch ->
            switch.setOn(isChecked)
//            switch.enabled = isChecked
        }
    )
}

class TEST() {
    fun getTest() {

    }
}

//class IOSPlatform1: Platform {
//    val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
//}


// Common Code (Shared)
actual class MyText actual constructor(text: String) {

}

@Composable
actual fun ComButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    UIKitButton(modifier = modifier, text)
}