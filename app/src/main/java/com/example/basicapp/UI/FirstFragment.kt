package com.example.basicapp.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.basicapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel = SuperHeroViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.heroesList.adapter = SuperHeroAdapter()


        binding.fab.setOnClickListener {
            viewModel.getHeroes()
        }

        viewModel.heroes.observe(viewLifecycleOwner){
            Log.d("SUPERHEROES", it.toString())
        }
        var token = (activity as SuperHeroesActivity).getPrefs("hola")
        Log.d("token first", token.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}