package org.test.testkmp

import android.app.Application
import android.content.Context

class KmpApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: Application? = null

        fun getAppContext(): Context {
            return instance!!.applicationContext
        }
    }


}