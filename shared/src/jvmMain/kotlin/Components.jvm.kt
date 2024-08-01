@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


// Common Code (Shared)
actual class MyText actual constructor(text: String)

@Composable
actual fun ComButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
) {
}