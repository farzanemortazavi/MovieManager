package com.example.moviemanager.features.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemanager.R
import com.example.moviemanager.utils.API_KEY
import kotlinx.android.synthetic.main.activity_search_movie.*

class searchMovieActivity : AppCompatActivity() {

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
        Toast.makeText(this,"movie id is "+id.toString(),Toast.LENGTH_SHORT).show()
    }
}
