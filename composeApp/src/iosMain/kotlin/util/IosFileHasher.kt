package util

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.allocArrayOf
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.usePinned
import platform.Foundation.*
import platform.posix.memcpy
import org.test.testkmp.objclib.kcrypto.KCrypto

actual object FileHasherFactory {
    actual fun createMd5Hasher(): FileHasher = FileMd5Hasher
}

@OptIn(ExperimentalForeignApi::class)
object FileMd5Hasher : FileHasher {
    override fun hash(data: ByteArray): String {
        return KCrypto.md5(data = data.toNSData())
    }

    fun hash(nsdata: NSData): String {
        return KCrypto.md5(data = nsdata)
    }
}

@OptIn(ExperimentalForeignApi::class)
fun NSData.toByteArray(): ByteArray = ByteArray(length.toInt()).apply {
    usePinned {
        memcpy(it.addressOf(0), bytes, length)
    }
}

@OptIn(ExperimentalForeignApi::class)
fun ByteArray.toNSData(): NSData = memScoped {
    NSData.create(bytes = allocArrayOf(this@toNSData), length = this@toNSData.size.toULong())
}
