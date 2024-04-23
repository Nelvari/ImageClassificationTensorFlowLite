package com.dicoding.asclepius.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.response.ArticlesItem
import com.dicoding.asclepius.response.NewsResponse
import com.dicoding.asclepius.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel(){

    private val _news = MutableLiveData<List<ArticlesItem>>()
    val news: LiveData<List<ArticlesItem>> = _news

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MainViewModel"
        private const val q = "cancer"
        private const val category = "health"
        private const val language = "en"
        private const val apiKey = "e07472da13b14d38b8a4395ff335fa08"
    }

    init {
        getNews(q, category, language, apiKey)
    }

    fun getNews(q: String, category: String, language: String, apiKey: String) {

        _isLoading.value = true

        val client = ApiConfig.getApiService().getNews(q, category, language, apiKey)
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _news.value = response.body()?.articles
                }
                else {
                    Log.e(TAG, "onFailures: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })

    }

}