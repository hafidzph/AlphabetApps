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

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val button : Button = itemView.findViewById(R.id.btn)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.button_abjad, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.button.text = data.listAbjad
        holder.button.setOnClickListener{
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