package com.example.moviemanager.features.offlineList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemanager.R
import com.example.moviemanager.base.DI.DaggerfactoryComplonent
import com.example.moviemanager.base.extensions.showToast
import com.example.moviemanager.features.details.movieDetailActivity
import kotlinx.android.synthetic.main.activity_offline_list.*

class offlineListActivity : AppCompatActivity(){

    lateinit var myViewModel:offlineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_list)

        val factory= DaggerfactoryComplonent.create().provideVMFactory()
        myViewModel= ViewModelProvider(this,factory).get(offlineViewModel::class.java)

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
        })

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
