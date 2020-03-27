package com.example.moviemanager.features.offlineList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.moviemanager.base.baseViewModel
import com.example.moviemanager.repository.local.localRepository
import com.example.moviemanager.repository.repositoryClass
import com.example.moviemanager.room.ImovieDao
import com.example.moviemanager.room.appDatabase
import com.example.moviemanager.room.movieTable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class offlineViewModel: baseViewModel() {

    val offlineResponse= MutableLiveData<List<movieTable>>()
    val offlineErrorRespose= MutableLiveData<String>()
    val disposable= CompositeDisposable()
    lateinit var db:appDatabase

    fun setInstanceOfDb(dataBaseInstance: appDatabase) {
        this.db = dataBaseInstance
    }
    val repository=localRepository(db.ImovieDao())



    fun getOfflineListResponseData(){

        disposable.add(
            repository.getOfflineList()
                .subscribe({
                    offlineResponse.value=it
                },{
                    Log.d("myError",it.message)
                    offlineErrorRespose.value="Error in getting offline list"
                })

        )

    }
    //--------------------------------------------------------------
    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}