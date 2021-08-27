package com.example.testapp.ui.activities.postsActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.R
import com.example.testapp.data.db.entities.Post
import com.example.testapp.databinding.ActivityPostsBinding
import com.example.testapp.ui.activities.postsDetailActivity.PostDetailActivity
import com.example.testapp.ui.adapters.OnPostClickListener
import com.example.testapp.ui.adapters.PostRecyclerAdapter
import com.example.testapp.utils.isOnline

import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class PostsActivity : AppCompatActivity() , KodeinAware, OnPostClickListener {
    override val kodein by kodein()
    private val factory : PostsViewModelFactory by instance()

    var binding: ActivityPostsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_posts
        )
        val viewModel = ViewModelProvider(this, factory).get(PostsViewModel::class.java)
        binding?.viewModel = viewModel

        if (isOnline(this)){
            viewModel.getPostsApiCall()
        }
        val adapter = PostRecyclerAdapter(this)
        binding?.recycler?.adapter = adapter

        viewModel.getPosts()?.observe(this, Observer { posts ->
            adapter.setPostList(posts)
        })
    }
    override fun onPostClicked(post: Post) {

        Intent(this, PostDetailActivity::class.java).apply {
            val bundle = Bundle()
            bundle.putParcelable("post", post)
            this.putExtras(bundle)
            startActivity(this)
        }

    }
}