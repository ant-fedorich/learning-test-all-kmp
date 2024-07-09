@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import androidx.compose.ui.Modifier


// Common Code (Shared)
actual class MyButton actual constructor(text: String, onClick: () -> Unit)
actual class MyText actual constructor(text: String)
actual class ComButton actual constructor(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
)

actual fun testFun() {
}

@Composable
actual fun ComButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
) {
}