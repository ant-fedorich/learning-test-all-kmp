package org.test.testkmp.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ui.App
import ui.component.SampleBox

@Preview
@Composable
fun BoxPreview() {
    SampleBox()
}

@Preview
@Composable
fun PreviewMain() {
    App()
}