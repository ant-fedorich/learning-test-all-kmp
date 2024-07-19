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


    private var x: Int = 0
    private var y: Int = 0

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


        window.addEventListener("click") { hide() }
        document.body?.appendChild(menuElement)
    }

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

        window.addEventListener("click") { hide() } // Hide on any click outside the menu
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
        this.x = x
        this.y = y
        menuElement.style.left = "${x}px"
        menuElement.style.top = "${y}px"
        menuElement.style.display = "block"
    }

    // show() method without parameters
    fun show() {
        menuElement.style.left = "${x}px"
        menuElement.style.top = "${y}px"
        menuElement.style.display = "block"
    }

    fun hide() {
        print("ContextMenu: hide triggered, hide menu: $menuElement, class: $this")
        menuElement.style.display = "none"
    }
}

actual class ContextMenuManager {
    val menus = mutableMapOf<Int, ContextMenu>()
    private var currentMenuId: Int? = null
    private var hidePreviousMenuScheduled = false // Flag to track scheduled hiding

    fun createContextMenu(id: Int, items: List<ContextMenuItem>) {
        print("ContextMenu: currentMenuId: ${currentMenuId}, id: $id, items: $items, \n menus: $menus")
        currentMenuId?.let {
            if (it != id) {
                val menu = menus[it]
                print("ContextMenu: currentMenuId: $it, id: $id before hiding menu: $menu")
                menus[it]?.hide()
            }
        }


        val menu = ContextMenu()
        items.forEach { menu.addMenuItem(it.label, it.onClick) }
        menus[id] = menu
        currentMenuId = id


//        // Clear any previously scheduled hiding
//        if (hidePreviousMenuScheduled) {
//            currentMenuId?.let {
//                window.clearTimeout(it)  // Clear the timeout
//                hidePreviousMenuScheduled = false
//            }
//        }
//
//        // Hide the previous menu only after a short delay
//        currentMenuId?.let {
//            hidePreviousMenuScheduled = true  // Set the flag
//            window.setTimeout({
//                hidePreviousMenuScheduled = false  // Reset the flag
//                menus[it]?.hide() as JsAny?
//            }, 10) // Adjust delay if needed
//        }

        // Show the new menu
    }
}

//actual class ContextMenuManager {
//    val menus = mutableMapOf<Int, ContextMenu>()
//    private var currentMenuId: Int? = null
//    private var currentMenu: ContextMenu? = null
//
//
//
//    fun createContextMenu(id: Int, items: List<ContextMenuItem>) {
//        val menu = ContextMenu()
//        items.forEach { menu.addMenuItem(it.label, it.onClick) }
//        menus[id] = menu
//
//        // Hide the previous menu only after a short delay
//        // (This allows the click event to propagate and the new menu to be shown)
//        currentMenuId?.let {
//            window.setTimeout({
//                menus[it]?.hide() as JsAny?
//            }, 10) // Adjust delay if needed
//        }
//        currentMenuId = id
//        menu.show()
//    }
//
//    private fun hideCurrentMenu() {
//        currentMenuId?.let { menus[it]?.hide() }
//    }
//
//}

data class ContextMenuItem(val label: String, val onClick: () -> Unit)
