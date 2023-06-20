package com.joeldev.rickandmortywiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joeldev.rickandmortywiki.databinding.ActivityEpisodeBinding
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

class EpisodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEpisodeBinding

    private lateinit var image: String
    private var rate by Delegates.notNull<Double>()
    private lateinit var duration: String
    private lateinit var name: String
    private var number by Delegates.notNull<Int>()
    private var season by Delegates.notNull<Int>()
    private lateinit var description: String
    override fun onCreate(savedInstanceState: Bundle?) {
        getExtras()
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        render()
    }

    private fun getExtras(){
        image = intent.getStringExtra("image")!!
        rate = intent.getDoubleExtra("rate", 0.0)
        duration = intent.getStringExtra("duration")!!
        name = intent.getStringExtra("name")!!
        number = intent.getIntExtra("number", 0)
        season = intent.getIntExtra("season", 0)
        description = intent.getStringExtra("description")!!
    }

    private fun render(){
        Picasso.get().load(image).into(binding.episodeImage)
        binding.rate.text = rate.toString() + " / 10"
        binding.duration.text = duration
        binding.episodeName.text = name
        binding.episodeNumber.text = season.toString() + "x" + number.toString()
        binding.episodeDescription.text = description.replace(Regex("<p>|</p>"), "")
    }
}