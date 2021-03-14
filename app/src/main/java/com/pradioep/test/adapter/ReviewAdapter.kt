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
import com.pradioep.test.model.MovieReview
import com.pradioep.test.util.UtilityHelper


class ReviewAdapter(private val context : Context, private val list : ArrayList<MovieReview>)
    : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_review, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        UtilityHelper.setImage(
                context,
                BuildConfig.IMAGE_URL+list[position].author_details.avatar_path,
                holder.imgUser
        )
        holder.tvName.text = list[position].author
        holder.tvDate.text = list[position].updated_at.subSequence(0, 10)
        holder.tvReview.text = list[position].content
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgUser: ImageView = itemView.findViewById(R.id.img_user)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        val tvReview: TextView = itemView.findViewById(R.id.tv_review)
    }
}