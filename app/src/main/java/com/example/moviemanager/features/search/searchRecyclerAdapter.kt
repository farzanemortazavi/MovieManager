package com.example.moviemanager.features.search
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemanager.R
import com.example.moviemanager.pojo.Result
import com.example.moviemanager.utils.IMAGE_BASE_URL
import com.example.moviemanager.utils.SMALL_PIC_SIZE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_recycler_item.view.*

class searchRecyclerAdapter(val moviewList:List<Result>, val clickListener:(Int)->Unit)
    : RecyclerView.Adapter<searchRecyclerAdapter.searchHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchHolder {
       val v=LayoutInflater.from(parent.context).inflate(R.layout.movie_recycler_item,parent,false)
        return searchHolder(v,clickListener)
    }

    override fun getItemCount(): Int {
        return moviewList.size
    }

    override fun onBindViewHolder(holder: searchHolder, position: Int) {
        holder.onBind(moviewList[position])
    }

    class searchHolder(val view: View,val clickListener:(Int)->Unit): RecyclerView.ViewHolder(view) {
        fun onBind(obj:Result){
            view.txtMovieItemTitle.text=obj.title
            view.txtMovieItemDate.text=obj.release_date
            view.txtMovieItemRank.text=obj.vote_average.toString()

            Picasso.get().load(IMAGE_BASE_URL+ SMALL_PIC_SIZE+obj.poster_path).into(view.imgMovieItemPoster)

            view.setOnClickListener{
                clickListener(obj.id)
            }

            }


    }
}
