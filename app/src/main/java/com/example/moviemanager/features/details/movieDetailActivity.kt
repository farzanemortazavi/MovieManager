package com.example.moviemanager.features.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.moviemanager.R
import com.example.moviemanager.base.baseActivity
import com.example.moviemanager.base.extensions.load
import com.example.moviemanager.pojo.movieResponseModel
import com.example.moviemanager.room.appDatabase
import com.example.moviemanager.utils.API_KEY
import com.example.moviemanager.utils.IMAGE_BASE_URL
import com.example.moviemanager.utils.LARGE_PIC_SIZE
import kotlinx.android.synthetic.main.activity_movie_detail.*

class movieDetailActivity : baseActivity() {
    lateinit var myViewModel:movieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        myViewModel= ViewModelProvider(this).get(movieDetailViewModel::class.java)

        val movieId=intent.getIntExtra("movieId",0)
        if(movieId==0){
            Toast.makeText(this,"wrong movie Id",Toast.LENGTH_SHORT)
        }
        else
        {
            showMovieDetails(movieId)

        }



    }
    //**************************************************
    fun showMovieDetails(id:Int)
    {
        myViewModel.getMovieDetailResponseData(id, API_KEY)
        myViewModel.detailResponse.observe(this, Observer {
            txtMovieTitle.text=it.title
            txtMovieRank.text=it.vote_average.toString()
            txtMovieReleaseDate.text=it.release_date
            txtMovieOverview.text=it.overview
            txtMovieGenres.text= getGenres(it)
            imgMovieBackdrop.load(IMAGE_BASE_URL + LARGE_PIC_SIZE+it.backdrop_path)


            }

        )
    }
    //*************************************************
    fun getGenres(movie:movieResponseModel):String{
        var result=""
        val listCount= movie.genres.size
        for(i in 0 until (listCount-1) ){
            result=result+movie.genres[i].name+", "
        }
        if(listCount>0)
            result=result+movie.genres[listCount-1].name

       // Log.d("myError","movie id is "+result)
        return result
    }
}
