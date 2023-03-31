package com.challenge.challengechapter3.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.challenge.challengechapter3.Adapter.AbjadDetailAdapter
import com.challenge.challengechapter3.Data.DataDetails
import com.challenge.challengechapter3.databinding.FragmentDetailAbjadBinding

class DetailAbjadFragment : Fragment() {
    lateinit var binding : FragmentDetailAbjadBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailAbjadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataAbjad = arguments?.getString("DATA_ABJAD")
        val dataDetails = DataDetails()
        val filteredData = arrayListOf<String>()
        for (i in 0 until dataDetails.detailWords.size){
            println(dataAbjad.equals(dataDetails.detailWords[i].substring(0, 1)))
            if(dataAbjad.equals(dataDetails.detailWords[i].substring(0, 1))) filteredData.add(dataDetails.detailWords[i])
        }
        val adapter = AbjadDetailAdapter(filteredData)
        adapter.submitData()
        binding.detail.layoutManager = LinearLayoutManager(requireContext())
        binding.detail.adapter = adapter
    }

}