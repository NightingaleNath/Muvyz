package com.codelytical.muvyz.core.util

import android.content.Context
import com.codelytical.muvyz.core.util.Constants

fun Context.appVersionName(): String {
    return try {
        val pInfo = this.packageManager.getPackageInfo(this.packageName, 0)
        val version = pInfo.versionName
        version
    } catch (e: Exception) {
        e.printStackTrace()
        "---"
    }
}

fun Context.appVersionCode(): Int {
    return try {
        val pInfo = this.packageManager.getPackageInfo(this.packageName, 0)
        val version = pInfo.versionCode
        version
    } catch (e: Exception) {
        e.printStackTrace()
        0
    }
}

fun String.createImageUrl(): String {
    return "${Constants.IMAGE_BASE_UR}/$this"
}
