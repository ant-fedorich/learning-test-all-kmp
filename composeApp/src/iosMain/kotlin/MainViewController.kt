import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.App
import ui.component.SampleBox

fun MainViewController() = ComposeUIViewController {
    //App()

    MainScreen()

    MyButton("Test") {

    }

}






@Composable
private fun MainScreen() {
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
//            Text(
//                text = "Title 11111",
//                fontSize = MaterialTheme.typography.h3.fontSize
//            )
            Spacer(modifier = Modifier.size(12.dp))
//            Text(text = "Hello")
//            SampleBox()
            MyButton("IOS Button") {

            }

            MyButton("Hello") {

            }

//            Button(
//                onClick = {}
//            ) {
//                Text("Click me")
//            }
            TextField("value", {})
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MainScreen()
}