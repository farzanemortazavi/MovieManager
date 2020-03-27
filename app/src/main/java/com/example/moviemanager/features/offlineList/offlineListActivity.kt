package com.example.moviemanager.features.offlineList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.moviemanager.R
import com.example.moviemanager.base.DI.DaggerfactoryComplonent
import com.example.moviemanager.base.extensions.logData
import com.example.moviemanager.base.extensions.showToast
import com.example.moviemanager.features.details.movieDetailActivity
import com.example.moviemanager.features.search.searchRecyclerAdapter
import com.example.moviemanager.room.appDatabase
import com.example.moviemanager.room.databaseBuilder
import com.example.moviemanager.utils.DATABASE_NAME
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_offline_list.*
import kotlinx.android.synthetic.main.activity_search_movie.*

class offlineListActivity : AppCompatActivity(){

    //lateinit var myViewModel:offlineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_list)

        /*

        //val factory= DaggerfactoryComplonent.create().provideVMFactory()
       myViewModel= ViewModelProvider(this).get(offlineViewModel::class.java)
        val db=databaseBuilder.getDatabasenIstance(this)
        myViewModel.setInstanceOfDb(db)

        myViewModel.getOfflineListResponseData()
        myViewModel.offlineResponse.observe(this, androidx.lifecycle.Observer {
            if(it!=null){

            val adapter=offlineListAdapter(it){
                showSearchDetail(it)
            }
            offlineRecycler.adapter=adapter
            offlineRecycler.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        }
        })

        myViewModel.offlineErrorRespose.observe(this, Observer {
            this.showToast(it)
        })*/

        val db=databaseBuilder.getDatabasenIstance(this)
        val disposable= CompositeDisposable()
        disposable.add(
            db.ImovieDao().getAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    this.logData("movie count is "+it.size)
                    val adapter= offlineListAdapter(it){

                    }
                    offlineRecycler.adapter=adapter
                    offlineRecycler.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)

                }){
                    this.logData(it.message.toString())
                    this.showToast("error in get movie list")
                }
        )

    }
    //---------------------------------------------------------------------
    private fun showSearchDetail(id:Int)
    {
        Log.d("myError","movie id is "+id.toString())
      //  val intent= Intent(this, movieDetailActivity::class.java)
      //  intent.putExtra("movieId",id)
     //   startActivity(intent)

    }
}
