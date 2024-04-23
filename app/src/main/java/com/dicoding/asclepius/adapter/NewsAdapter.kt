package com.dicoding.asclepius.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.databinding.HistoryListBinding
import com.dicoding.asclepius.response.ArticlesItem

class NewsAdapter :  ListAdapter<ArticlesItem, NewsAdapter.MyViewHolder>(DIFF_CALLBACK){

    class MyViewHolder(private val binding: HistoryListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: ArticlesItem){
            binding.tvResult.text = result.title
            binding.tvPersen.text = result.description
            Glide.with(binding.root)
                .load(result.urlToImage)
                .into(binding.ivImage)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
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