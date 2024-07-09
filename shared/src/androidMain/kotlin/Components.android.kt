import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


actual class MyText actual constructor(text: String) {

}

@Composable
actual fun ComButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text)
    }
}