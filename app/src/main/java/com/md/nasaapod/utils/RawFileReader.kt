package com.md.nasaapod.utils

import android.content.Context
import androidx.annotation.RawRes
import java.io.IOException

class RawFileReader(private val context: Context) : FileReader() {

    @Throws(IOException::class)
    fun readFile(@RawRes rawFileId: Int): String {
        val fileStream = context.resources.openRawResource(rawFileId)
        return getFileContent(fileStream)
    }
}