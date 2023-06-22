package com.example.basicapp.ui.heroes.heroeslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.basicapp.R
import com.example.basicapp.ui.heroes.model.Superhero
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
        private val superheroFav = itemView.findViewById<ImageView>(R.id.fav_heart_empty)

        private lateinit var superhero: Superhero

        init {
            itemView.setOnClickListener {
                onClick(superhero.id)
            }
        }

        fun bind(superHero : Superhero) {
            // TODO: Add also in this view the fav functionality
            if(superHero.favorite){
                superheroFav.setImageResource(R.drawable.heart_fill_frame)
            }
            else{
                superheroFav.setImageResource(R.drawable.heart_empty_frame)
            }

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