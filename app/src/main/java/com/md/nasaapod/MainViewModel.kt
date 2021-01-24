package com.md.nasaapod

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.md.nasaapod.picture_list.data.Picture
import com.md.nasaapod.picture_list.data.PictureRepository
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel @ViewModelInject constructor(val pictureRepository: PictureRepository) :
    ViewModel() {

    // region Mutable LiveData
    private val pictureListLiveData = MutableLiveData<List<Picture>>()
    // endregion

    // region LiveData getters
    fun pictureListLiveData() = pictureListLiveData as LiveData<List<Picture>>
    // endregion

    fun fetchPictures() {
        pictureRepository.fetchPictures()
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<List<Picture>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: List<Picture>) {
                    pictureListLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: $e")
                }
            })
    }

    companion object{
        private val TAG = MainViewModel::class.java.simpleName
    }
}