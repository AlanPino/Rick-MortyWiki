package com.joeldev.rickandmortywiki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.joeldev.rickandmortywiki.databinding.ActivitySeasonBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SeasonActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeasonBinding
    private lateinit var retrofit: Retrofit
    private lateinit var seasons: List<Season>
    private lateinit var episodeAdapter: EpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeasonBinding.inflate(layoutInflater)
        setContentView(binding.root)


        retrofit = getRetrofit()

        searchSeasons()
        searchEpisodes(882)
        initUI()
    }

    private fun initUI() {
        episodeAdapter = EpisodeAdapter { onEpisodeSelected(it) }
        binding.rvEpisode.setHasFixedSize(true)
        binding.rvEpisode.layoutManager = LinearLayoutManager(this)
        binding.rvEpisode.adapter = episodeAdapter
    }

    private fun initSeasonAdapter() {
        val seasonsAdapter = ArrayAdapter(
            this,
            R.layout.dropdown_item,
            seasons.map { "Season ${it.number}" })
        binding.seasonMenu.setAdapter(seasonsAdapter)

        binding.seasonMenu.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                searchEpisodes(seasons[position].id)
            }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.tvmaze.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchSeasons() {
        CoroutineScope(Dispatchers.IO).launch {
            val query = retrofit.create(ApiService::class.java).getSeasons()

            runOnUiThread {
                seasons = query
                initSeasonAdapter()
            }
        }
    }

    private fun searchEpisodes(seasonId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val query = retrofit.create(ApiService::class.java).getEpisodes(seasonId)

            runOnUiThread {
                episodeAdapter.episodeList = query
                episodeAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun onEpisodeSelected(episode: Episode) {
        val intent = Intent(this, EpisodeActivity::class.java)
        intent.putExtra("image", episode.image.url)
        intent.putExtra("rate", episode.rate.rating)
        intent.putExtra("duration", episode.duration)
        intent.putExtra("name", episode.name)
        intent.putExtra("number", episode.number)
        intent.putExtra("season", episode.season)
        intent.putExtra("description", episode.description)
        startActivity(intent)
    }
}