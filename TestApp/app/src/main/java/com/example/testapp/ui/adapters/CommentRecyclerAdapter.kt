package com.example.testapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.data.db.entities.Comment

import com.example.testapp.databinding.AdapterCommentBinding

class CommentRecyclerAdapter:
    RecyclerView.Adapter<CommentRecyclerAdapter.CommentViewHolder>() {
    private var comment = mutableListOf<Comment>()
    fun setCommentList(post: List<Comment>) {
        this.comment = post.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CommentRecyclerAdapter.CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCommentBinding.inflate(inflater, parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentRecyclerAdapter.CommentViewHolder, position: Int) {
        holder.bind(comment[position])

    }
    override fun getItemCount(): Int {
        return comment.size
    }
    class CommentViewHolder(private val binding: AdapterCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment){
            binding.emailTv.text = comment.email
            binding.nameTv.text = comment.name
            binding.commentTv.text = comment.body
        }
    }
}
