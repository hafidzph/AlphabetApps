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

class AbjadDetailAdapter(val listDetail: ArrayList<String> = DataDetails().detailWords): RecyclerView.Adapter<AbjadDetailAdapter.ViewHolder>() {
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

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val button : Button = itemView.findViewById(R.id.btnDetail)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.button_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.button.text = differ.currentList[position]
        holder.button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${differ.currentList[position]}"))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}