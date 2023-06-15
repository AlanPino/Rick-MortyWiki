package com.joeldev.rickandmortywiki

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.joeldev.rickandmortywiki.databinding.EpisodePreviewBinding
import com.squareup.picasso.Picasso

class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val binding = EpisodePreviewBinding.bind(view)

    fun bind(episode: Episode){
        binding.episodeNumber.text = "Episode " + episode.number.toString()
        binding.episodeName.text = episode.name
        binding.episodeDescription.text = episode.description.replace(Regex("<p>|</p>"), "")
        Picasso.get().load(episode.image.url).into(binding.episodeImage)
    }
}