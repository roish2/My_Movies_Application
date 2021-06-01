package com.example.mymoviesapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviesapplication.model.MovieMainObject
import com.example.mymoviesapplication.model.MovieResponse

class MainFragment : Fragment(), RecyclerMoviesAdapter.OnMovieClickListener {
    private lateinit var recyclerView: RecyclerView
    private var adapterData:ArrayList<MovieMainObject> = ArrayList()
    private lateinit var viewModel: MainViewModel

    companion object {
        const val MAIN_DATA_KEY = "main_data"
        fun newInstance(data:MovieResponse) :MainFragment{
            val fragment = MainFragment()
            val bundle = Bundle()
            bundle.putParcelable(MAIN_DATA_KEY, data)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = layout.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

       getBundleData()
        context?.let {
            recyclerView.adapter = RecyclerMoviesAdapter(it, adapterData, this)
        }

        return layout
    }

    fun getBundleData(){
        arguments?.let {
            val data = it.getParcelable<MovieResponse>(MAIN_DATA_KEY)
            data?.movieMainObject?.let {
                adapterData = it
            }
        }
    }

    override fun onMovieClick(data: MovieMainObject) {
        viewModel.goToMovieDetails(data)
    }


}