package com.md.nasaapod.utils

import android.content.Context
import androidx.annotation.RawRes

class RawFileReader(private val context: Context) : FileReader() {

    fun readFile(@RawRes rawFileId: Int): String {
        val fileStream = context.resources.openRawResource(rawFileId)
        return getFileContent(fileStream)
    }
}