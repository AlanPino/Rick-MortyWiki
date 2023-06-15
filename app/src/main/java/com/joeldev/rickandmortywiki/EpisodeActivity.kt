package com.joeldev.rickandmortywiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joeldev.rickandmortywiki.databinding.ActivityEpisodeBinding
import com.joeldev.rickandmortywiki.databinding.ActivitySeasonBinding

class EpisodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEpisodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}