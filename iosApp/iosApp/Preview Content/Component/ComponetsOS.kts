// In your iOS code, you need to implement the UIKitButton composable
@Composable
fun UIKitButton(text: String, onClick: () -> Unit) {
    val button = UIButton()
    button.setTitle(text, UIControlStateNormal)
    button.setOnTouchUpInside {
        onClick()
    }
    // Use UIKit to wrap the UIKitButton into a Compose view
    UIViewWrapper(button)
}