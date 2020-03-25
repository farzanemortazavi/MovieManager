package com.example.moviemanager.features.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviemanager.base.baseViewModel
import com.example.moviemanager.pojo.movieResponseModel
import com.example.moviemanager.pojo.searchResponseModel
import com.example.moviemanager.repository.repositoryClass
import io.reactivex.disposables.CompositeDisposable

class movieDetailViewModel:baseViewModel() {

    val detailResponse= MutableLiveData<movieResponseModel>()
    val detailErrorRespose= MutableLiveData<String>()

    private val repository= repositoryClass()
    val disposable= CompositeDisposable()

    fun getMovieDetailResponseData(id:Int,apiKey:String){
        disposable.add(
            repository.getMovieDetails(id,apiKey)
                .subscribe({
                    detailResponse.value=it
                },{
                    Log.d("myError",it.message)
                    detailErrorRespose.value="Error in retrieving movie data"
                })

        )

    }

}