@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import kotlinx.cinterop.*
import platform.CoreGraphics.CGRectMake
import platform.UIKit.*
import platform.Foundation.*
import platform.objc.*


@OptIn(ExperimentalForeignApi::class)
actual class MyButton actual constructor(text: String, onClick: () -> Unit) : UIButton(frame = CGRectMake(0.0, 0.0, 100.0, 44.0)) {

    init {
        setTitle(text, forState = UIControlStateNormal)
        this.addTarget(target = this, action = NSSelectorFromString("handleClick"), forControlEvents = UIControlEventTouchUpInside)
    }

    @OptIn(BetaInteropApi::class)
    @ObjCAction private fun handleClick() {
        val rawPointer = objc_getAssociatedObject(this, NSSelectorFromString("handleClick"))

        if (rawPointer != null) {
            // Cast the raw pointer back to a StableRef<() -> Unit>
            val clickHandlerRef = rawPointer as StableRef<() -> Unit>

            // Get the lambda and invoke it
            clickHandlerRef.get().invoke()
        }
    }
}




//@OptIn(ExperimentalForeignApi::class)
//actual class MyButton actual constructor(text: String, onClick: () -> Unit) {
//    private val uiButton: UIButton = UIButton()
//
//    init {
//        uiButton.setTitle(text, forState = UIControlStateNormal)
//
//        uiButton.addTarget(
//            target = this,
//            action = staticCFunction { thisRef: COpaquePointer? ->
//                thisRef?.asStableRef<MyButton>()?.get()?.on
//            },
//            forControlEvents = UIControlEventTouchUpInside
//        )
//    }
//
//    // ... (Add more properties/methods as needed)
//}