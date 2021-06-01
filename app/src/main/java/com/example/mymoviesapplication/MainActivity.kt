package com.example.mymoviesapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.steps.observe(this, stepsObserver)
        viewModel.getMovies()
    }

    private val stepsObserver = Observer<MainViewModel.Steps> { step ->
            when(step){
                is MainViewModel.Steps.DataReady ->{
                    val fragment = MainFragment.newInstance(step.data)
                    fragmentManager.beginTransaction().add(R.id.container, fragment).commit()

                }

                is MainViewModel.Steps.movieDetails ->{
                    val fragment = DetailsFragment.newInstance(step.data)
                    fragmentManager.beginTransaction().add(R.id.container, fragment).addToBackStack("backStack").commit()
                }
                is MainViewModel.Steps.OnError ->{
//                TODO() show error
                }
            }
    }
}