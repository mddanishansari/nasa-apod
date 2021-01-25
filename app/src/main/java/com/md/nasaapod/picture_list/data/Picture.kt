package com.md.nasaapod.picture_list.data

import com.fasterxml.jackson.annotation.JsonProperty
import java.text.SimpleDateFormat
import java.util.*

data class Picture(
    @JsonProperty("copyright")
    val copyright: String?,
    @JsonProperty("date")
    private val dateInString: String,
    @JsonProperty("explanation")
    val explanation: String,
    @JsonProperty("hdurl")
    val hdImageUrl: String,
    @JsonProperty("media_type")
    val mediaType: String,
    @JsonProperty("service_version")
    val serviceVersion: String,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("url")
    val thumbnailUrl: String,
) {
    val date: Date
        get() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateInString)

}