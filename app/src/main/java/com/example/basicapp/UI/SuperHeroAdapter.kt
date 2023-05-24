package com.example.basicapp.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.basicapp.R

class SuperHeroAdapter : RecyclerView.Adapter<SuperHeroAdapter.SuperHeroViewHolder>(){

    val heroesList = mutableListOf<String>()

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

    class SuperHeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val superheroName = itemView.findViewById<TextView>(R.id.textView2)

        fun bind(superHero : String) {
            superheroName.text = superHero
        }
    }

}