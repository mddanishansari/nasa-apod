package com.md.nasaapod.picture_list.data

sealed class PictureListState {
    object Loading : PictureListState()
    data class Success(val pictureList: List<Picture>) : PictureListState()
    data class Failed(val e: Throwable) : PictureListState()
}