package com.example.moviemanager.features.offlineList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemanager.R
import com.example.moviemanager.base.extensions.load
import com.example.moviemanager.room.movieTable
import com.example.moviemanager.utils.IMAGE_BASE_URL
import com.example.moviemanager.utils.SMALL_PIC_SIZE
import kotlinx.android.synthetic.main.movie_recycler_item.view.*

class offlineListAdapter(val offlineList:List<movieTable>,val clickListener:(Int)->Unit): RecyclerView.Adapter<offlineListAdapter.offlineHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): offlineHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.movie_recycler_item,parent,false)
        return offlineHolder(v,clickListener)
    }

    override fun getItemCount(): Int {
       return offlineList.size
    }

    override fun onBindViewHolder(holder: offlineHolder, position: Int) {
        holder.onBind(offlineList[position])
    }

    class offlineHolder(val view:View,val clickListener:(Int)->Unit):RecyclerView.ViewHolder(view){

        fun onBind(obj:movieTable){
            view.txtMovieItemTitle.text=obj.title
            view.txtMovieItemDate.text=obj.releaseDate
            view.txtMovieItemRank.text=obj.rank.toString()

            // Picasso.get().load(IMAGE_BASE_URL+ SMALL_PIC_SIZE+obj.poster_path).into(view.imgMovieItemPoster)
            view.imgMovieItemPoster.load(IMAGE_BASE_URL+ SMALL_PIC_SIZE+obj.posterUrl)

            view.setOnClickListener{
                clickListener(obj.id)
            }

        }


    }

}