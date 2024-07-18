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
//    var titleText = ""




    val menu = ContextMenu()
    menu.addMenuItem("Option 1") {
        titleText = "Option 1"
//        window.asDynamic().console.log("Option 1 selected")
    }
    menu.addMenuItem("Option 2") {
        titleText = "Option 2"
//        window.asDynamic().console.log("Option 2 selected")
    }
    menu.addMenuItem("Option 3") {
        titleText = "Option 3"
//        window.asDynamic().console.log("Option 3 selected")
    }



//    // After the WebMainScreen is composed, we add the ContextMenu to the body
//    document.addEventListener("DOMContentLoaded") {
//        val body = document.body!!
//        menu.attachTo(body)
//    }



    ComposeViewport(document.body!!) {
        WebMainScreen(titleText)
    }

    menu.attachTo(document.body!!) // Attach to the body element

}