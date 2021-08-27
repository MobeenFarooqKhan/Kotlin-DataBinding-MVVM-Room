package com.example.testapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.db.entities.Post
import com.example.testapp.databinding.AdapterPostsBinding

class PostRecyclerAdapter (private val listener: OnPostClickListener):
    RecyclerView.Adapter<PostRecyclerAdapter.PostsViewHolder>() {
    private var posts = mutableListOf<Post>()
    fun setPostList(post: List<Post>) {
        this.posts = post.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PostRecyclerAdapter.PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterPostsBinding.inflate(inflater, parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostRecyclerAdapter.PostsViewHolder, position: Int) {
        holder.bind(posts[position],listener)

    }
    override fun getItemCount(): Int {
        return posts.size
    }
    class PostsViewHolder(private val binding: AdapterPostsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post, listener: OnPostClickListener){
            binding.postTitle.text = post.title
            binding.postId.text = "${post.id}"
            binding.userId.text = "${post.userId}"
            itemView.setOnClickListener {
                listener.onPostClicked(post = post)
            }
        }
    }
}
interface OnPostClickListener {
    fun onPostClicked(post : Post)
}