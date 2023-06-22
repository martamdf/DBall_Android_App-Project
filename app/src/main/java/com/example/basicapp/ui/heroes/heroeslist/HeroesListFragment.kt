package com.example.basicapp.ui.heroes.heroeslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.basicapp.ui.heroes.SuperHeroesActivity
import com.example.basicapp.databinding.FragmentHeroesListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroesListFragment : Fragment() {

    private var _binding: FragmentHeroesListBinding? = null

    private val viewModel: SuperHeroesViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeroesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SuperHeroAdapter() { superheroId: String ->
            findNavController().navigate(
                HeroesListFragmentDirections.actionHeroesListToHeroDetail(
                    superheroId
                )
                /*HeroesListFragmentDirections.actionFirstFragmentToSecondFragment(
                    superheroId
                )*/
            )
        }

        binding.heroesList.adapter = adapter
        viewModel.getHeroes()

        viewModel.heroes.observe(viewLifecycleOwner){ heroes ->
            adapter.heroesList.clear()
            adapter.heroesList.addAll(heroes)
            adapter.notifyDataSetChanged()
        }

        val token = (activity as SuperHeroesActivity).getPrefs("hola")
        Log.d("token first", token.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}