package com.example.moviemanager.features.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemanager.R
import com.example.moviemanager.base.DI.DaggerfactoryComplonent
import com.example.moviemanager.base.DI.factoryComplonent
import com.example.moviemanager.base.baseActivity
import com.example.moviemanager.base.DI.viewModelFactory
import com.example.moviemanager.base.extensions.isInternetAvailable
import com.example.moviemanager.base.extensions.showToast
import com.example.moviemanager.features.details.movieDetailActivity
import com.example.moviemanager.features.offlineList.offlineListActivity
import com.example.moviemanager.repository.network.networkRepository
import com.example.moviemanager.repository.repositoryClass
import com.example.moviemanager.utils.API_KEY
import kotlinx.android.synthetic.main.activity_search_movie.*

class searchMovieActivity : baseActivity() {

    lateinit var myViewModel:searchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        val factory=DaggerfactoryComplonent.create().provideVMFactory()

        myViewModel=ViewModelProvider(this,factory).get(searchViewModel::class.java)

        btnMenu.setOnClickListener{
            try {
                drawer.openDrawer(GravityCompat.START)

            }
            catch (e:Exception){
                Log.d("myMain", e.message)
            }}


        btnSearch.setOnClickListener{

            /*if(this.isInternetAvailable())
                this.showToast("Please check your internet connection")*/

            myViewModel.getSearchResponseData(API_KEY,edtSearch.text.toString().trim())
            progressBar.visibility=ProgressBar.VISIBLE
        }

        myViewModel.searchResponse.observe(this, Observer {
            val result=it.results
            val adapter=searchRecyclerAdapter(result){
                showSearchDetail(it)
            }
            searchRecycler.adapter=adapter
            searchRecycler.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
            progressBar.visibility=ProgressBar.GONE
        })

        myViewModel.searchErrorRespose.observe(this, Observer {
            this.showToast(it)
            progressBar.visibility=ProgressBar.GONE
        })


        menuOffline.setOnClickListener {
            val myIntent=Intent(this, offlineListActivity::class.java)
            startActivity(myIntent)


        }





    }
    //---------------------------------------------------------------------
    private fun showSearchDetail(id:Int)
    {
        Log.d("myError","movie id is "+id.toString())
        val intent=Intent(this,movieDetailActivity::class.java)
        intent.putExtra("movieId",id)
        startActivity(intent)

    }
}
