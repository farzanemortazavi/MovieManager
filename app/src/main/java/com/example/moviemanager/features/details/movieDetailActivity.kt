package com.example.moviemanager.features.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.moviemanager.R
import com.example.moviemanager.base.DI.DaggerfactoryComplonent
import com.example.moviemanager.base.baseActivity
import com.example.moviemanager.base.extensions.load
import com.example.moviemanager.base.extensions.logData
import com.example.moviemanager.base.extensions.showToast
import com.example.moviemanager.pojo.movieResponseModel
import com.example.moviemanager.room.appDatabase
import com.example.moviemanager.room.databaseBuilder
import com.example.moviemanager.room.movieTable
import com.example.moviemanager.utils.API_KEY
import com.example.moviemanager.utils.DATABASE_NAME
import com.example.moviemanager.utils.IMAGE_BASE_URL
import com.example.moviemanager.utils.LARGE_PIC_SIZE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_detail.*

class movieDetailActivity : baseActivity() {
    lateinit var myViewModel:movieDetailViewModel
    lateinit var movieObj:movieTable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val factory=DaggerfactoryComplonent.create().provideVMFactory()
        myViewModel= ViewModelProvider(this, factory).get(movieDetailViewModel::class.java)

        val movieId=intent.getIntExtra("movieId",0)
        if(movieId==0){
            this.showToast("wrong movie Id")
        }
        else
        {
            showMovieDetails(movieId)

        }
        val db=databaseBuilder.getDatabasenIstance(this)


        btnSave.setOnClickListener{
            myViewModel.detailResponse.observe(this, Observer {
                val movie=movieTable(it.id,it.title,it.vote_average.toString(),it.release_date,it.overview
                ,txtMovieGenres.text.toString(),it.poster_path,it.backdrop_path)
                //myViewModel.saveMovieDetails(movie)

                this.logData("movieId is: "+movie.id.toString())



                db.ImovieDao().insertMovie(movie)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        this.showToast("movie is saved")

                    },{

                        this.logData(it.message.toString())
                        this.showToast("error in saving movie")
                    })


            })


        }

        myViewModel.detailErrorRespose.observe(this, Observer {
            this.showToast(it)
        })



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
    //----------------------------------------------------
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
    //---------------------------------------------------
}
