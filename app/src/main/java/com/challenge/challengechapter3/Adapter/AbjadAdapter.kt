package com.challenge.challengechapter3.Adapter


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.challenge.challengechapter3.Data.DataWords
import com.challenge.challengechapter3.DetailAbjadFragment
import com.challenge.challengechapter3.R
import com.challenge.challengechapter3.databinding.ButtonAbjadBinding


class AbjadAdapter(val context: Context): RecyclerView.Adapter<AbjadAdapter.ViewHolder>() {
    private var diffCallback = object : DiffUtil.ItemCallback<DataWords>(){
        override fun areItemsTheSame(oldItem: DataWords, newItem: DataWords): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataWords, newItem: DataWords): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: ArrayList<DataWords>) = differ.submitList(value)

    inner class ViewHolder(val binding: ButtonAbjadBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ButtonAbjadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.btn.text = data.listAbjad
        holder.binding.btn.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("DATA_ABJAD", data.listAbjad)
            val detailAbjadFragment = DetailAbjadFragment()
            detailAbjadFragment.arguments = bundle
            (context as AppCompatActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.frame, detailAbjadFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}