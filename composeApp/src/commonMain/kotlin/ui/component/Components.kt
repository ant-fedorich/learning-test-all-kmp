package ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun SampleBox() {
    Box(
        modifier = Modifier
            .height(60.dp).fillMaxWidth()
            .background(Color.Red)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello World",
            fontSize = 24.sp
        )
    }
}


@Preview
@Composable
private fun Preview() {
    SampleBox()
}


//// commonMain module
//@Composable
//fun CommonButton(text: String, onClick: () -> Unit) {
//    PlatformButton(text, onClick)
//}

//// iosMain module
//@Composable
//fun PlatformButton(text: String, onClick: () -> Unit) {
//    UIKitButton(
//        text = text,
//        onClick = onClick
//    )
//}

//// In your iOS code, you need to implement the UIKitButton composable
//@Composable
//fun UIKitButton(text: String, onClick: () -> Unit) {
//    val button = UIButton()
//    button.setTitle(text, UIControlStateNormal)
//    button.setOnTouchUpInside {
//        onClick()
//    }
//    // Use UIKit to wrap the UIKitButton into a Compose view
//    UIViewWrapper(button)
//}
