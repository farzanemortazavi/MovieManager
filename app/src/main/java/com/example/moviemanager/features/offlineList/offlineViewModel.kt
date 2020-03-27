package com.example.moviemanager.features.offlineList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviemanager.base.baseViewModel
import com.example.moviemanager.repository.repositoryClass
import com.example.moviemanager.room.movieTable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class offlineViewModel @Inject constructor(val repository: repositoryClass): baseViewModel() {
    val offlineResponse= MutableLiveData<List<movieTable>>()
    val offlineErrorRespose= MutableLiveData<String>()
    val disposable= CompositeDisposable()

    fun getOfflineListResponseData(){
        /*disposable.add(
            repository.getOfflineMoveList()
                .subscribe({
                    offlineResponse.value=it
                },{
                    Log.d("myError",it.message)
                    offlineErrorRespose.value="Error in retrieving search data"
                })

        )*/

    }
    //--------------------------------------------------------------
    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}