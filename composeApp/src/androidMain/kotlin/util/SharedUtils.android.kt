package util

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import org.test.testkmp.KmpApp

actual fun sendSMS(number: String, message: String) {
    val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("smsto:$number")
        putExtra("sms_body", message)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK

    }
    val context = KmpApp.getAppContext()

    try {
        startActivity(context, smsIntent, null)
    } catch (e: Exception) {
        println(e)
    }
}