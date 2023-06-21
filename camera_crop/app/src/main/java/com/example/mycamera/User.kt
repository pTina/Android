package com.example.mycamera

import android.app.Application
import android.graphics.Bitmap

class User : Application() {
    var isPermission:Boolean? = null

    var photo:Bitmap? = null
}