package com.line.nab.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.line.nab.R
import com.line.nab.common.WeatherAdapter
import com.line.nab.common.WeatherObj
import com.line.nab.databinding.MainFragmentBinding
import com.line.nab.di.Injectable
import com.line.nab.model.Status
import com.line.nab.model.WeatherDb
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: MainFragmentBinding

    private lateinit var adapter: WeatherAdapter

    val mainViewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickGetWeatherListener()
        registerListener()
        adapter = WeatherAdapter()
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(activity)

        val dividerItemDecoration = DividerItemDecoration(
            activity,
            LinearLayoutManager(activity).orientation
        )
        binding.list.addItemDecoration(dividerItemDecoration)
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun clickGetWeatherListener() {
        binding.btnGetWeather.setOnClickListener({
            val nameOfCity = binding.input.text.toString()
            if (!nameOfCity.isNullOrEmpty()) {
                doGetWeatherByCity(nameOfCity = nameOfCity)
            }
        })
    }

    private fun registerListener() {
        mainViewModel.result.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it.status) {
                    Status.SUCCESS -> {
                        parseAdapter(it.data!!)
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

    }

    private fun doGetWeatherByCity(nameOfCity: String) {
        mainViewModel.getWeatherByCityName(nameOfCity)
    }

    private fun parseAdapter(objs: List<WeatherDb>) {
        val items = mutableListOf<WeatherObj>()
        objs.forEach {
            val date = miliToDate(it.dt)
            val avg = calculatorAvg(it.tempMin!!, it.tempMax!!)
            val weatherObj = WeatherObj(
                date,
                avg,
                it.pressure!!.toString(),
                it.humidity.toString(),
                it.description.toString()
            )
            items.add(weatherObj)
        }
        adapter.addItems(items)
    }

    private fun miliToDate(mili: Long): String {
        val date = Date(mili * 1000)
        val dateFormat = SimpleDateFormat("EEE, d MMM yyyy")
        return dateFormat.format(date)
    }

    private fun calculatorAvg(min: Double, max: Double): String {
        return ((min + max) / 2).toInt().toString()
    }
}