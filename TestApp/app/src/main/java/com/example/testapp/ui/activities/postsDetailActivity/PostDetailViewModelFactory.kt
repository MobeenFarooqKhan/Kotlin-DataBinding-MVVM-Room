package com.example.testapp.ui.activities.postsDetailActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.data.repository.CommentsRepository
import com.example.testapp.data.repository.PostRepository
import com.example.testapp.data.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class PostDetailViewModelFactory(
    private val repository: UserRepository,
    private val commentsRepository: CommentsRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostDetailViewModel(repository,commentsRepository) as T
    }
}