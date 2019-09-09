

package com.example.multiplescreen.anko.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imastudio.kotlinlanjutanpertama.R
import com.imastudio.kotlinlanjutanpertama.model.Anime

class AnimeAdapter(private var animes: List<Anime>, private val animeClickListener: OnAnimeClickListener) : RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.item_anime, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val anime = animes[position]
    val name = anime.name
    val ranking = anime.ranking.toString()

    holder.name.text = "$ranking-$name"
    holder.image.setImageResource(anime.image)
    holder.view.tag = anime

    holder.view.setOnClickListener { clickedView ->
      val selectedAnime = clickedView.tag as Anime
      animeClickListener.onClick(selectedAnime)
    }

  }

  override fun getItemCount(): Int = animes.size

  inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val name = view.findViewById<TextView>(R.id.name)
    val image = view.findViewById<ImageView>(R.id.image)

  }

}

interface OnAnimeClickListener {
  fun onClick(anime: Anime)
}
