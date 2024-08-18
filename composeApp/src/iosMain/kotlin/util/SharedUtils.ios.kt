package util

import kotlinx.cinterop.ExperimentalForeignApi
import org.test.testkmp.objclib.sms.SmsHandler

@OptIn(ExperimentalForeignApi::class)
actual fun sendSMS(number: String, message: String) {
    SmsHandler.sendSmsWithNumber(number, message)
}

