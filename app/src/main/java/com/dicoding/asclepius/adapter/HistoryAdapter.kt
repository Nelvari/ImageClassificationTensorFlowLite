package com.dicoding.asclepius.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.database.History
import com.dicoding.asclepius.databinding.HistoryListBinding

class HistoryAdapter :
    ListAdapter<History, HistoryAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: HistoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: History) {
            val uri = Uri.parse(result.image)
            Log.i("adapter", "bind: $uri")
//            uri.let {
//                binding.ivImage.setImageURI(it)
//            }
            binding.tvResult.text = result.result
            binding.tvPersen.text = result.confidence
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = HistoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }

}