package com.md.nasaapod.picture_list.data

import com.fasterxml.jackson.databind.json.JsonMapper
import com.md.nasaapod.R
import com.md.nasaapod.utils.RawFileReader
import io.reactivex.rxjava3.core.Single
import java.io.IOException

class PictureRepositoryImpl(
    private val fileReader: RawFileReader,
    private val jsonMapper: JsonMapper,
) : PictureRepository {
    override fun fetchPictures(): Single<List<Picture>> {
        return Single.create { emitter ->
            try {
                // read raw json file
                val pictureJson = fileReader.readFile(R.raw.picture_data)

                // convert json to list of Picture
                val pictureList =
                    jsonMapper.readValue(pictureJson, Array<Picture>::class.java).toList()

                // emit the data
                emitter.onSuccess(pictureList)
            } catch (e: IOException) {
                // emit error
                emitter.onError(e)
            }
        }
    }

}