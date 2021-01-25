package com.md.nasaapod.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

open class FileReader {
    @Throws(IOException::class)
    protected fun getFileContent(inputStream: InputStream): String {
        val fileContentStringBuilder = StringBuilder()
        val fileBufferedReader = BufferedReader(InputStreamReader(inputStream))
        var line: String?
        while (fileBufferedReader.readLine().also { line = it } != null) {
            fileContentStringBuilder
                .append(line)
                .appendLine()
        }
        fileBufferedReader.close()
        return fileContentStringBuilder.toString()
    }
}