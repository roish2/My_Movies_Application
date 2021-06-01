package com.example.mymoviesapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymoviesapplication.model.MovieMainObject

class RecyclerMoviesAdapter(val context: Context, var data: ArrayList<MovieMainObject>, val listener:OnMovieClickListener) : RecyclerView.Adapter<RecyclerMoviesAdapter.MyViewHolder>() {

    interface OnMovieClickListener{
        fun onMovieClick(data:MovieMainObject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_adapter_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.onBind(data[position])
        }

        inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
            private val movieTitle: TextView = itemView.findViewById(R.id.title)
            private val movieReleaseDate: TextView = itemView.findViewById(R.id.release_date)
            private val averageVote: TextView = itemView.findViewById(R.id.average_vote)

            fun onBind(data:MovieMainObject){
                movieTitle.text = data.title
                movieReleaseDate.text = data.releaseDate
                averageVote.text = data.voteAverage.toString()
                Glide.with(context)
                    .load(MainViewModel.BASE_IMAGE_URL+data.posterPath)
                    .centerInside()
                    .into(movieImage)

                itemView.setOnClickListener {
                    listener.onMovieClick(data)
                }
            }

        }
    }
