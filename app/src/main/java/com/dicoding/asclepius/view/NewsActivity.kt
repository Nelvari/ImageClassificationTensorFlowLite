package com.dicoding.asclepius.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.adapter.NewsAdapter
import com.dicoding.asclepius.databinding.ActivityNewsBinding
import com.dicoding.asclepius.response.ArticlesItem
import com.dicoding.asclepius.viewModel.NewsViewModel

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    private val newsViewModel by viewModels<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvNews.addItemDecoration(itemDecoration)

        newsViewModel.news.observe(this) { news ->
            setNewsData(news)
        }

        newsViewModel.isLoading.observe(this) {
            showLoading(it)
        }

    }

    private fun setNewsData(result: List<ArticlesItem>?) {
        val adapter = NewsAdapter()
        adapter.submitList(result)
        binding.rvNews.adapter = adapter
    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }

}