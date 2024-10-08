package ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.component.SampleBox
import util.AppConsts
import util.sendSMS

@Composable
fun MainScreen(
    onButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .imePadding()
//            .background(Color(0xFFFFE8EE))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Magenta)
                .systemBarsPadding()
//                .clip(AbsoluteRoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = AppConsts.TITLE,
                fontSize = MaterialTheme.typography.h3.fontSize
            )
            Spacer(modifier = Modifier.size(12.dp))
            SampleBox()
            Spacer(Modifier.size(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                onClick = onButtonClick
            ) {
                Text("Click me")
            }
            Spacer(Modifier.size(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                sendSMS(
                    number = "+18885551212",
                    message = "Hello World"
                )
            }) {
                Text("Open Message App")
            }


            Spacer(Modifier.size(16.dp))
            TextField(
                modifier = Modifier.fillMaxWidth().height(60.dp),
                value = "value",
                onValueChange = {}
            )
        }
    }
}