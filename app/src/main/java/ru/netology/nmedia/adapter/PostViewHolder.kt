package ru.netology.nmedia.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.checkСount
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

class PostViewHolder(
    private val binding: CardPostBinding,
    private val likeListener: OnLikeListener,
    private val shareListener: OnShareListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            countLikes.text = checkСount(post.likes)
            countShare.text = checkСount(post.shared)
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_baseline_favorite_red_24dp else R.drawable.ic_baseline_favorite_24dp
            )
            like.setOnClickListener {
                likeListener(post)
            }
            share.setOnClickListener {
                shareListener(post)
            }
        }
    }
}