package com.example.wordsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.FragmentWordListBinding

const val SEARCH_PREFIX = "https://www.google.com/search?q="

class WordListFragment : Fragment() {

    private val wordListFragmentArgs: WordListFragmentArgs by navArgs()
    private var _binding: FragmentWordListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        val letterId = wordListFragmentArgs.letter
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = WordAdapter(letterId, context) { item ->
                val queryUrl: Uri = Uri.parse("$SEARCH_PREFIX$item")
                val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                startActivity(intent)
            }
            addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}