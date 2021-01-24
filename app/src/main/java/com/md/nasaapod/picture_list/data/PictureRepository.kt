package com.md.nasaapod.picture_list.data

import io.reactivex.rxjava3.core.Single

interface PictureRepository {
    fun fetchPictures(): Single<List<Picture>>
}