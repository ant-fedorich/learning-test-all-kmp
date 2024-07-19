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
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerButton
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import kotlinx.browser.document
import kotlinx.coroutines.delay
import org.w3c.dom.AddEventListenerOptions
import org.w3c.dom.events.MouseEvent
import ui.component.SampleBox

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WebMainScreen(titleText: String, menuManager: ContextMenuManager) {
    var currentPointerEvent by remember { mutableStateOf<PointerEvent?>(null) }
    var contextMenuId by remember { mutableStateOf(0) }
    var showMenu by remember { mutableStateOf(false) } // State to control menu visibility



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
            ComButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                text = "Click me!"
            )
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
                    .onPointerEvent(PointerEventType.Press) { pointerEvent ->
                        if (pointerEvent.button == PointerButton.Secondary) {
                            showMenu = true
                            contextMenuId++

                            pointerEvent.nativeEvent // Prevent default context menu

                            menuManager.createContextMenu(contextMenuId,
                                listOf(
                                    ContextMenuItem("Cut") { /* Handle cut action */ },
                                    ContextMenuItem("Copy") { /* Handle copy action */ },
                                    ContextMenuItem("Paste") { /* Handle paste action */ }
                                ))

                            val mouseEvent = pointerEvent.nativeEvent as MouseEvent

                            // Show the menu immediately after creation
                            menuManager.menus[contextMenuId - 1]?.show(
                                mouseEvent.clientX, mouseEvent.clientY
                            )
                        }
                    }
                    .height(60.dp),
                value = "value",
                onValueChange = {}
            )
        }

        currentPointerEvent?.let { pointerEvent ->
            if (pointerEvent.button == PointerButton.Secondary) { // Check for right-click
                menuManager.createContextMenu(
                    contextMenuId++,  // Pass the PointerId
                    listOf(
                        ContextMenuItem("Cut") { /* Handle cut action */ },
                        ContextMenuItem("Copy") { /* Handle copy action */ },
                        ContextMenuItem("Paste") { /* Handle paste action */ }
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(3f) // Takes the remaining space
                .fillMaxHeight() // Ensures it fills the available height
                .padding(8.dp)
        ) {
            SelectionContainer {
                Column(
                    modifier = Modifier
                        .clip(AbsoluteRoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(8.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = titleText,
                        fontSize = MaterialTheme.typography.h3.fontSize
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    SampleBox()
                    Spacer(Modifier.size(16.dp))
                }
            }
        }
    }
    // Outside of the TextField scope
    LaunchedEffect(showMenu) {
        if (showMenu) {
            // Wait for a brief moment to allow the context menu to render
            delay(100)

            // Prevent the default context menu after the delay
            document.addEventListener("contextmenu", { event ->
                event.preventDefault()
            }, options = AddEventListenerOptions(once = false))

            // Reset showMenu state
            showMenu = false
        }
    }
}


