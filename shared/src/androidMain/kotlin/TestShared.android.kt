import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
actual fun MainScreenShared() {
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
                modifier = Modifier.fillMaxWidth(),
                text = "Title 11111",
                fontSize = MaterialTheme.typography.h3.fontSize
            )
            Spacer(modifier = Modifier.size(12.dp))
            SampleBox()
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text("Click me")
            }
            TextField(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                value = "value",
                onValueChange = {}
            )
        }
    }
}


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