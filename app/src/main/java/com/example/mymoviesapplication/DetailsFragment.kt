package com.example.mymoviesapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mymoviesapplication.model.MovieMainObject
import com.example.mymoviesapplication.model.MovieResponse

class DetailsFragment : Fragment() {

    private var data:MovieMainObject? = null

    companion object {
        const val DATA_KEY = "details_data"

        fun newInstance(data:MovieMainObject):DetailsFragment{
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(DATA_KEY, data)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_details, container, false)

        val movieImage: ImageView = layout.findViewById(R.id.movie_image)
        val movieTitle: TextView = layout.findViewById(R.id.title)
        val details: TextView = layout.findViewById(R.id.details)
        val movieReleaseDate: TextView = layout.findViewById(R.id.release_date)
        val averageVote: TextView = layout.findViewById(R.id.average_vote)

        getBundleData()

        movieTitle.text = data?.title
        movieReleaseDate.text = data?.releaseDate
        averageVote.text = data?.voteAverage.toString()
        details.text = data?.overview
        context?.let {
            Glide.with(it)
                .load(MainViewModel.BASE_IMAGE_URL+data?.posterPath)
                .centerInside()
                .into(movieImage)
        }


        return layout
    }

    fun getBundleData(){
        arguments?.let {
             data = it.getParcelable<MovieMainObject>(DATA_KEY)

        }
    }
}