

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.modifier.modifierLocalMapOf

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
actual fun MainScreenShared() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE0A6A6))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(AbsoluteRoundedCornerShape(46.dp))
                .background(Color.White)
                .padding(20.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Title iOS",
                fontSize = MaterialTheme.typography.h4.fontSize
            )
            Spacer(modifier = Modifier.size(16.dp))
            SampleBox()
            Spacer(modifier = Modifier.size(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                onClick = {}
            ) {
                Text("Click button")
            }
            Spacer(modifier = Modifier.size(16.dp))

            UIKitTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
//                    .padding(20.dp)
//                    .border(1.dp, Color.Red)
            )

            Spacer(modifier = Modifier.size(16.dp))

            UIKitButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                text = "IOS Button"
            )
            Spacer(modifier = Modifier.size(16.dp))

            UICheckbox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
//                    .padding(20.dp)
//                    .border(1.dp, Color.Red)
            )
            Spacer(modifier = Modifier.size(16.dp))


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                value = "type here",
                onValueChange = {}
            )
        }
    }
}

@Composable
fun SampleBox() {
    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(Color.Magenta)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello???",
            fontSize = 24.sp
        )
    }
}