package com.dicoding.asclepius.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.adapter.HistoryAdapter
import com.dicoding.asclepius.database.History
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.viewModel.HistoryViewModel
import com.dicoding.asclepius.viewModel.ViewModelFactory

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    private val historyViewModel by viewModels<HistoryViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvHistory.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvHistory.addItemDecoration(itemDecoration)

        historyViewModel.getAllFavorite().observe(this) { result ->
            val items = arrayListOf<History>()
            result.map {
                val item = History(image = it.image, result = it.result, confidence = it.confidence)
                items.add(item)
            }
            setHistoryData(items)
        }

    }

    private fun setHistoryData(result: List<History>) {
        val adapter = HistoryAdapter()
        adapter.submitList(result)
        binding.rvHistory.setHasFixedSize(true)
        binding.rvHistory.adapter = adapter
    }

}