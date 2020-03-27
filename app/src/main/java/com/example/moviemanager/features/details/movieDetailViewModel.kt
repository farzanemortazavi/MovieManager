package com.example.moviemanager.features.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviemanager.base.baseViewModel
import com.example.moviemanager.pojo.movieResponseModel
import com.example.moviemanager.pojo.searchResponseModel
import com.example.moviemanager.repository.repositoryClass
import com.example.moviemanager.room.movieTable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class movieDetailViewModel @Inject constructor(val repository:repositoryClass):baseViewModel() {

    val detailResponse= MutableLiveData<movieResponseModel>()
    val detailErrorRespose= MutableLiveData<String>()
    private var isMovieSaved = false

    //private val repository= repositoryClass()
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
    //---------------------------------------------------------
    fun saveMovieDetails(movie:movieTable){
        /*disposable.add(repository.saveMovieDetails(movie)
            .subscribe({
                    isMovieSaved=true
            },{
                Log.d("myError",it.message)
                detailErrorRespose.value="Error in saving movie data"
            })
        )*/
    }
    //---------------------------------------------------------
    fun deleteMovieDetails(id:Int){
        /*disposable.add(repository.deleteMovie(id)
            .subscribe(
                {
                    isMovieSaved=false
                },{
                    Log.d("myError",it.message)
                    detailErrorRespose.value="Error in delete movie data"
                }
            )

        )*/
    }
    //---------------------------------------------------------
    fun onClickedOnSaveButton(movie:movieTable) {
        when (isMovieSaved) {
            true -> deleteMovieDetails(movie.id)
            false -> saveMovieDetails(movie)
        }
    }
    //---------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}