package com.md.nasaapod.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

open class FileReader {
    protected fun getFileContent(inputStream: InputStream): String {
        val fileContentStringBuilder = StringBuilder()
        var fileBufferedReader: BufferedReader? = null
        try {
            fileBufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (fileBufferedReader.readLine().also { line = it } != null) {
                fileContentStringBuilder
                        .append(line)
                        .append("\n")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fileBufferedReader?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return fileContentStringBuilder.toString()
    }
}