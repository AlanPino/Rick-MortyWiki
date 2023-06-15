package com.joeldev.rickandmortywiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.joeldev.rickandmortywiki.databinding.ActivitySeasonBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class SeasonActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeasonBinding
    private lateinit var retrofit: Retrofit
    private lateinit var episodeAdapter: EpisodeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeasonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        retrofit = getRetrofit()

        CoroutineScope(Dispatchers.IO).launch {
            val query = retrofit.create(ApiService::class.java).getEpisodes(882)

            runOnUiThread {
                episodeAdapter.episodeList = query.toList()
                episodeAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initUI(){
        episodeAdapter = EpisodeAdapter()
        binding.rvEpisode.setHasFixedSize(true)
        binding.rvEpisode.layoutManager = LinearLayoutManager(this)
        binding.rvEpisode.adapter = episodeAdapter
    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}