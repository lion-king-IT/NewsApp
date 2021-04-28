package com.reo.running.newsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.reo.running.newsapp.databinding.FragmentNewsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private var response: Response<ResponseData>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_news, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newsService = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            response = newsService.getNews("475a6716e0b1401ea0191041db0e6fff", "jp")
            if (response!!.isSuccessful) {
                val users = response!!.body()
                Log.d("debug", users.toString())
            }
            withContext(Dispatchers.Main) {
                binding.run {
                    newsRecyclerview.adapter =
                        response!!.body()?.articles?.let {
                            NewsRecyclerViewAdapter(requireContext(),
                                it.filter { !it.content.isNullOrEmpty() })
                        }
                    newsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }
}