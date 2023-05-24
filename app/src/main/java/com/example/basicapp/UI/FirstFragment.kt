package com.example.basicapp.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.basicapp.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val viewModel: SuperHeroViewModel by viewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SuperHeroAdapter()
        binding.heroesList.adapter = adapter

        viewModel.getHeroes()

        binding.fab.setOnClickListener {

        }

        viewModel.heroes.observe(viewLifecycleOwner){ heroes ->
            Log.d("SUPERHEROES", heroes.toString())
            val superHeroNames = heroes.map { hero ->
                hero.name
            }
            adapter.heroesList.clear()
            adapter.heroesList.addAll(superHeroNames)
            adapter.notifyDataSetChanged()
        }



        var token = (activity as SuperHeroesActivity).getPrefs("hola")
        Log.d("token first", token.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}