package com.md.nasaapod

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.md.nasaapod.picture_list.data.Picture
import com.md.nasaapod.picture_list.data.PictureListState
import com.md.nasaapod.picture_list.data.PictureRepository
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel @ViewModelInject constructor(val pictureRepository: PictureRepository) :
    ViewModel() {

    private val disposable = CompositeDisposable()

    // region Mutable LiveData
    private val pictureListLiveData = MutableLiveData<PictureListState>()
    // endregion

    // region LiveData getters
    fun pictureListStateLiveData() = pictureListLiveData as LiveData<PictureListState>
    // endregion

    fun fetchPictures() {
        pictureListLiveData.value = PictureListState.Loading
        pictureRepository.fetchPictures()
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<List<Picture>> {
                override fun onSubscribe(d: Disposable) {
                    disposable.add(d)
                }

                override fun onSuccess(t: List<Picture>) {
                    pictureListLiveData.postValue(PictureListState.Success(t))
                }

                override fun onError(e: Throwable) {
                    pictureListLiveData.postValue(PictureListState.Failed(e))
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }
}