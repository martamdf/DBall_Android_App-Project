package com.example.basicapp.UI.heroes.heroeslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.basicapp.UI.heroes.SuperHeroesActivity
import com.example.basicapp.UI.heroes.model.Superhero
import com.example.basicapp.databinding.FragmentFirstBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val viewModel: SuperHeroesViewModel by viewModels()
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

        val adapter = SuperHeroAdapter() { superheroId: String ->
            findNavController().navigate(
                FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                    superheroId
                )
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