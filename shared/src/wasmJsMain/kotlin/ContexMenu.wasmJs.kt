@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent

actual class ContextMenu {
    private val menuElement: HTMLDivElement = document.createElement("div") as HTMLDivElement
    private val menuItems = mutableListOf<HTMLButtonElement>()

    init {
        // Styling
        menuElement.classList.add("context-menu")
        menuElement.style.display = "none"
        menuElement.style.position = "fixed"
        menuElement.style.zIndex = "1000"
        menuElement.style.backgroundColor = "#f0f0f0"
        menuElement.style.border = "1px solid #ccc"

        menuElement.style.padding = "8px"
        menuElement.style.display = "flex"
        menuElement.style.flexDirection = "column"



        // Attach to the DOM
        document.body?.appendChild(menuElement)


        // Target a specific element (e.g., a div) where you want the context menu
//        val targetElement = document.getElementById("myDiv") as HTMLElement?

//        // Event listeners
//        targetElement?.addEventListener("contextmenu") { event ->
//            event.preventDefault()
//            val mouseEvent = event as MouseEvent
//            if (mouseEvent.button.toInt() == 2) { // Right-click
//                show(mouseEvent.clientX, mouseEvent.clientY)
//            }
//        }

//        // Event listeners
//        document.addEventListener("contextmenu") { event ->
//            event.preventDefault()
//            val mouseEvent = event as MouseEvent
//            if (mouseEvent.button.toInt() == 2) {
//                show(mouseEvent.clientX, mouseEvent.clientY)
//            }
//        }

//        window.addEventListener("click") { hide() } // Hide on any click
    }

//    fun attachTo(element: HTMLElement) {
//        // Attach the event listener to the specific element
//        element.addEventListener("contextmenu") { event ->
//            event.preventDefault()
//            val mouseEvent = event as MouseEvent
//            if (mouseEvent.button.toInt() == 2) {
//                show(mouseEvent.clientX, mouseEvent.clientY)
//            }
//        }
//
//        // ... other initialization code ...
//    }

    fun attachTo(element: HTMLElement) {
        // Attach the event listener to the specific element
        element.addEventListener("contextmenu") { event ->
            event.preventDefault() // Prevent default browser context menu
            val mouseEvent = event as MouseEvent
            if (mouseEvent.button.toInt() == 2) { // Check for right-click
                show(mouseEvent.clientX, mouseEvent.clientY)
            }
        }

        // Append the menu to the body only after the event listener is attached
        document.body?.appendChild(menuElement) // Move this line here

        window.addEventListener("click", { hide() }) // Hide on any click outside the menu
    }

    // Add this function to append items:
    fun addMenuItem(label: String, onClick: () -> Unit) {
        val menuItem = document.createElement("button") as HTMLButtonElement
        menuItem.textContent = label

        // Kotlin-based styling for each menu item button (menuItem)
        menuItem.style.width = "100%"
        menuItem.style.padding = "8px"
        menuItem.style.marginBottom = "4px"
        menuItem.style.backgroundColor = "#fff"
        menuItem.style.border = "1px solid #ccc"
        menuItem.style.cursor = "pointer"

        menuItem.onclick = {
            onClick()
            hide()
        }
        menuItems.add(menuItem)
        menuElement.appendChild(menuItem)
    }

    actual fun show(x: Int, y: Int) {
        menuElement.style.left = "${x}px"
        menuElement.style.top = "${y}px"
        menuElement.style.display = "block"
    }

    private fun hide() {
        menuElement.style.display = "none"
    }
}

