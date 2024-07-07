import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.chozzle.composemacostheme.MacButton
import io.chozzle.composemacostheme.MacCheckbox
import io.chozzle.composemacostheme.MacRadioButton
import io.chozzle.composemacostheme.MacSecondaryButton
import io.chozzle.composemacostheme.MacTheme
import io.chozzle.composemacostheme.modifiedofficial.MacOutlinedTextField
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.App
import ui.component.SampleBox

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "testkmp",
    ) {
        MainScreen()
    }
}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFE8EE))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(AbsoluteRoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = "Title 11111",
                fontSize = MaterialTheme.typography.h3.fontSize
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(text = "Hello")
            SampleBox()

            MacTheme {
                MacButton(
                    modifier = Modifier.fillMaxWidth().height(64.dp).padding(8.dp),
                    onClick = {},
                ) {
                    Text("Hello")
                }
                MacOutlinedTextField("value", {}, modifier = Modifier.fillMaxWidth().height(64.dp).padding(8.dp))
                MacSecondaryButton({}, modifier = Modifier.fillMaxWidth().height(64.dp)) {
                    Text("Title")
                }
                Spacer(modifier = Modifier.height(16.dp))
                MacCheckbox(false, {}, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.height(16.dp))

                MacCheckbox(true, {}, modifier = Modifier.size(26.dp))
                Spacer(modifier = Modifier.height(16.dp))

                MacCheckbox(true, {}, modifier = Modifier.size(16.dp, 32.dp))
                Spacer(modifier = Modifier.height(16.dp))

                Spacer(Modifier.height(16.dp))
                MacRadioButton(true, {}, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.height(16.dp))

                MacRadioButton(false, {}, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.height(16.dp))

            }

//            Button(
//                onClick = {}
//            ) {
//                Text("Click me")
//            }
//            TextField("value", {})
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MainScreen()
}