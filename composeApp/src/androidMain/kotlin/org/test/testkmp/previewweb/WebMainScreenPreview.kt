package org.test.testkmp.previewweb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ui.component.SampleBox
import util.AppConsts

@Preview(device = "id:desktop_small")
@Composable
fun WebMainScreenPreview() {
    Row(
        modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFFE8EE))
        .padding(8.dp)
        .padding(bottom = 36.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Button(
                modifier = Modifier,
                onClick = {},
            ) {
                Text(text = "Click me!!")

            }
            Spacer(Modifier.size(16.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                value = "value",
                onValueChange = {}
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                value = "value",
                onValueChange = {}
            )
        }

        Column(
            modifier = Modifier
                .weight(3f) // Takes the remaining space
                .fillMaxHeight() // Ensures it fills the available height
                .padding(8.dp)
        ) {
                Column(
                    modifier = Modifier
//                    .clip(AbsoluteRoundedCornerShape(16.dp))
//                    .background(Color.White)
//                    .padding(8.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = AppConsts.TITLE,
                        fontSize = MaterialTheme.typography.h3.fontSize
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    SampleBox()
                    Spacer(Modifier.size(16.dp))
                }
        }
    }
}

