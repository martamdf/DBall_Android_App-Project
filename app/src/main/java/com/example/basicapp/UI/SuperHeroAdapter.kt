package com.example.basicapp.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basicapp.R
import com.example.basicapp.UI.model.Superhero
import com.squareup.picasso.Picasso

class SuperHeroAdapter (private val onClick: (String) -> (Unit)) :
    ListAdapter<Superhero, SuperHeroAdapter.SuperHeroViewHolder>(SuperheroDiffCallback()) {

    val heroesList = mutableListOf<Superhero>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.heroe_cell, parent, false)
        return SuperHeroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  heroesList.size
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(heroesList[position])
    }

    inner class SuperHeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val superheroName = itemView.findViewById<TextView>(R.id.textView2)
        private val superheroImage = itemView.findViewById<ImageView>(R.id.hero_image)
        private val superheroFav = itemView.findViewById<ImageView>(R.id.is_fav)
        private val superheroNotFav = itemView.findViewById<ImageView>(R.id.fav_heart_empty)

        private lateinit var superhero: Superhero

        init {
            itemView.setOnClickListener {
                onClick(superhero.id)
            }
            superheroFav.setOnClickListener {
                superheroFav.setImageResource(R.drawable.heart_empty)
            }
            superheroNotFav.setOnClickListener {
                superheroNotFav.setImageResource(R.drawable.heart_fill)
            }

        }

        fun bind(superHero : Superhero) {

            superheroFav.isVisible = superHero.favorite
            superheroName.text = superHero.name
            Picasso.get().load(superHero.photo).into(superheroImage)
            this.superhero = superHero
        }
    }

    class SuperheroDiffCallback: DiffUtil.ItemCallback<Superhero>() {
        override fun areItemsTheSame(oldItem: Superhero, newItem: Superhero): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Superhero, newItem: Superhero): Boolean {
            return oldItem == newItem
        }
    }
}