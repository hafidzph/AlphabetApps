package com.challenge.challengechapter3.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.challenge.challengechapter3.Data.DataDetails
import com.challenge.challengechapter3.Data.DataWords
import com.challenge.challengechapter3.R
import com.challenge.challengechapter3.databinding.ButtonDetailBinding

class AbjadDetailAdapter(val listDetail: ArrayList<String>): RecyclerView.Adapter<AbjadDetailAdapter.ViewHolder>() {
    private var diffCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    fun submitData() = differ.submitList(listDetail)

    inner class ViewHolder(val binding: ButtonDetailBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = ButtonDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.btnDetail.text = differ.currentList[position]
        holder.binding.btnDetail.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${differ.currentList[position]}"))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}