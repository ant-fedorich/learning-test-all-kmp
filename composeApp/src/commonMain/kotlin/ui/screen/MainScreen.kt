package ui.screen

import ComButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import testkmp.composeapp.generated.resources.Res
import ui.component.SampleBox
import utils.AppConsts

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
                modifier = Modifier.fillMaxWidth(),
                text = AppConsts.TITLE,
                fontSize = MaterialTheme.typography.h3.fontSize
            )
            Spacer(modifier = Modifier.size(12.dp))
            SampleBox()

            ComButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                text = "Click me"
            )
            Spacer(Modifier.size(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                onClick = {}
            ) {
                Text("Click me")
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