package com.example.lessons11.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lessons11.data.model.Weather
import com.example.lessons11.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        homeViewModel.getWeather()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


   private fun renderData(appState: AppState){
      when(appState){
          is AppState.Error -> {
              binding.loadingLayout.visibility = View.GONE
              /*Snackbar.make(binding.mainView,"Error",Snackbar.LENGTH_INDEFINITE).setAction("Reload"){

              }*/
          }
          AppState.Loading -> {
              binding.loadingLayout.visibility = View.VISIBLE
          }
          is AppState.Success -> {
              val weatherData = appState.weatherData
              binding.loadingLayout.visibility = View.GONE
              setData(weatherData)
          }
      }
   }

    private fun setData(weatherData:Weather){
        binding.cityName.text = weatherData.city.city
        binding.cityCoordinates.text = String.format(
            weatherData.city.lat,
            weatherData.city.lon
        )
        binding.temperatureValue.text = weatherData.temperature.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}