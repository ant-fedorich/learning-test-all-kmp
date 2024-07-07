import platform.UIKit.UIDevice
import kotlin.native.concurrent.isFrozen

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

fun test() {
//    getPlatform().isFrozen
}