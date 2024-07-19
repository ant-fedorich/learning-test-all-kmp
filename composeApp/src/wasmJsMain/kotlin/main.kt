import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.events.MouseEvent
import ui.App

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    var titleText by mutableStateOf("Initial Title")

    val contextMenuManager = ContextMenuManager()



    ComposeViewport(document.body!!) {
        WebMainScreen(titleText, contextMenuManager)
    }

//    menu.attachTo(document.body!!) // Attach to the body element

}