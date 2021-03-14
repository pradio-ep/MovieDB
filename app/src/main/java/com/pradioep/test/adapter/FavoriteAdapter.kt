package com.pradioep.test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pradioep.test.BuildConfig
import com.pradioep.test.R
import com.pradioep.test.model.MovieDetail
import com.pradioep.test.util.UtilityHelper


class FavoriteAdapter(private val context : Context, private val list : ArrayList<MovieDetail>,
                      private val listener: MovieListener) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    interface MovieListener{
        fun onFavoriteClicked(movieDetail: MovieDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_movie, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.llMovie.setOnClickListener {
            listener.onFavoriteClicked(list[position])
        }
        UtilityHelper.setImage(
                context,
                BuildConfig.IMAGE_URL+list[position].poster_path,
                holder.imgMovie
        )
        holder.tvTitle.text = list[position].title
        holder.tvRelaseDate.text = list[position].release_date
        holder.tvOverview.text = list[position].overview
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val llMovie: LinearLayout = itemView.findViewById(R.id.ll_movie)
        val imgMovie: ImageView = itemView.findViewById(R.id.img_movie)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvRelaseDate: TextView = itemView.findViewById(R.id.tv_relase_date)
        val tvOverview: TextView = itemView.findViewById(R.id.tv_overview)
    }
}