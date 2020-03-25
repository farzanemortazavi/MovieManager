package com.example.moviemanager.features.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemanager.R
import com.example.moviemanager.base.baseActivity
import com.example.moviemanager.features.details.movieDetailActivity
import com.example.moviemanager.utils.API_KEY
import kotlinx.android.synthetic.main.activity_search_movie.*

class searchMovieActivity : baseActivity() {

    lateinit var myViewModel:searchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        myViewModel=ViewModelProvider(this).get(searchViewModel::class.java)


        btnSearch.setOnClickListener{
            myViewModel.getSearchResponseData(API_KEY,edtSearch.text.toString().trim())
        }

        myViewModel.searchResponse.observe(this, Observer {
            val result=it.results
            val adapter=searchRecyclerAdapter(result){
                showSearchDetail(it)
            }
            searchRecycler.adapter=adapter
            searchRecycler.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        })



    }
    //*******************************************
    private fun showSearchDetail(id:Int)
    {
        Log.d("myError","movie id is "+id.toString())
        val intent=Intent(this,movieDetailActivity::class.java)
        intent.putExtra("movieId",id)
        startActivity(intent)

    }
}
