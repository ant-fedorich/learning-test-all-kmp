@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


actual class MyText actual constructor(text: String)


@Composable
actual fun ComButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    Button(
        modifier = modifier,
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Cyan
        )
    ) {
        Text(text)
    }
}