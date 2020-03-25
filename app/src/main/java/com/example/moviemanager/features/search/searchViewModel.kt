package com.example.moviemanager.features.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviemanager.base.baseViewModel
import com.example.moviemanager.pojo.searchResponseModel
import com.example.moviemanager.repository.repositoryClass
import io.reactivex.disposables.CompositeDisposable

class searchViewModel(): baseViewModel() {
    val searchResponse=MutableLiveData<searchResponseModel>()
    val searchErrorRespose=MutableLiveData<String>()

    private val repository=repositoryClass()
    val disposable= CompositeDisposable()


    fun getSearchResponseData(apiKey:String,searchPhrase:String){
        disposable.add(
            repository.getMovieList(apiKey,searchPhrase)
                .subscribe({
                    searchResponse.value=it
                },{
                    Log.d("myError",it.message)
                    searchErrorRespose.value="Error in retrieving search data"
                })

        )

    }



    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}