package com.dicoding.asclepius.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.database.History
import com.dicoding.asclepius.repository.HistoryRepository

class HistoryViewModel(application: Application) : ViewModel() {

    private val mHistoryRepository: HistoryRepository = HistoryRepository(application)

    fun getAllFavorite(): LiveData<List<History>> = mHistoryRepository.getAllHistory()

    fun insert(history: History) {
        mHistoryRepository.insert(history)
    }

}