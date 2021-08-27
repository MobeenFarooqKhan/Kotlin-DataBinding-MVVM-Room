package com.example.testapp.ui.activities.postsActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.data.repository.PostRepository

@Suppress("UNCHECKED_CAST")
class PostsViewModelFactory(
    private val repository: PostRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostsViewModel(repository) as T
    }
}