package com.challenge.challengechapter3.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.challengechapter3.Adapter.AbjadAdapter
import com.challenge.challengechapter3.Data.DataWords
import com.challenge.challengechapter3.R
import com.challenge.challengechapter3.databinding.FragmentAbjadBinding

class AbjadFragment : Fragment() {
    private lateinit var binding: FragmentAbjadBinding
    private var isGrid = true
    private var gridItem: MenuItem? = null
    private var linearItem: MenuItem? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAbjadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listAbjad = arrayListOf<DataWords>()
        ('A').rangeTo('Z').forEach { c ->
            listAbjad.add(DataWords(c.toString()))
        }

        recyclerView = view.findViewById(R.id.abjad)
        val adapter = AbjadAdapter()

        adapter.submitData(listAbjad)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        gridItem?.isVisible = !isGrid
        linearItem?.isVisible = isGrid
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.custom_menu, menu)
        gridItem = menu.findItem(R.id.changeGrid)
        linearItem = menu.findItem(R.id.changeVertical)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.changeGrid -> {
                recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                recyclerView.adapter?.notifyDataSetChanged()
                gridItem?.isVisible = false
                linearItem?.isVisible = true
                isGrid = true
                true
            }
            R.id.changeVertical -> {
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter?.notifyDataSetChanged()
                gridItem?.isVisible = true
                linearItem?.isVisible = false
                isGrid = false
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
