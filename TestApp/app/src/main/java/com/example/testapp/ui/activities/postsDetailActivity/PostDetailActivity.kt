package com.example.testapp.ui.activities.postsDetailActivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.testapp.R
import com.example.testapp.data.db.entities.Post
import com.example.testapp.databinding.ActivityPostsDetailBinding
import com.example.testapp.utils.LogData
import com.example.testapp.utils.isOnline
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class PostDetailActivity  : AppCompatActivity() , KodeinAware {

    override val kodein by kodein()
    private val factory: PostDetailViewModelFactory by instance()
    var binding: ActivityPostsDetailBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_posts_detail
        )
        val viewModel = ViewModelProvider(this, factory).get(PostDetailViewModel::class.java)
        val post = intent.getParcelableExtra<Post>("post")
        binding?.viewModel = viewModel
        post?.let {
            viewModel.getUser(this,it.userId)
        }
        viewModel.getSelectedUser()?.observe(this, Observer { user ->
            user?.let {
                LogData("Select user Name is :${user?.name}")
            }
        })
    }
}

