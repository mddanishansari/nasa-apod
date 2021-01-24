package com.md.nasaapod.picture_list.model

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.assertj.core.api.Assertions.assertThat

import org.junit.Before
import org.junit.Test
import java.util.*


class PictureTest {
    private lateinit var jsonMapper: JsonMapper

    @Before
    fun setUp() {
        jsonMapper = JsonMapper().apply {
            registerModule(KotlinModule())
        }
    }

    @Test
    fun `JsonObject is deserialized properly to Picture`() {
        val input = """
              {
                "copyright": "A",
                "date": "2019-12-01",
                "explanation": "B",
                "hdurl": "C",
                "media_type": "D",
                "service_version": "E",
                "title": "F",
                "url": "G"
              }
        """.trimIndent()

        val result = jsonMapper.readValue(input, Picture::class.java)

        assertThat(result).isEqualTo(fakePicture)
    }

    @Test
    fun `JsonArray is deserialized properly to list of Picture`() {
        val input = """
            [
              {
                "copyright": "A",
                "date": "2019-12-01",
                "explanation": "B",
                "hdurl": "C",
                "media_type": "D",
                "service_version": "E",
                "title": "F",
                "url": "G"
              },
              {
                "copyright": "A",
                "date": "2019-11-01",
                "explanation": "B",
                "hdurl": "C",
                "media_type": "D",
                "service_version": "E",
                "title": "F",
                "url": "G"
              }
            ]
            """.trimIndent()

        val result = jsonMapper.readValue(input, Array<Picture>::class.java).toList()

        assertThat(result).hasSize(2)
    }

    @Test
    fun `Date is correct after deserializing Json`() {
        val input = """
              {
                "copyright": "A",
                "date": "2019-12-01",
                "explanation": "B",
                "hdurl": "C",
                "media_type": "D",
                "service_version": "E",
                "title": "F",
                "url": "G"
              }
        """.trimIndent()
        val calendar = Calendar.getInstance()
        calendar.set(2019, 11, 1, 0, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        val result = jsonMapper.readValue(input, Picture::class.java)

        assertThat(result.date).isEqualTo(calendar.time)
    }
}