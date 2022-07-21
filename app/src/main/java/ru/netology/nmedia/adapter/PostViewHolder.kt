package ru.netology.nmedia.adapter

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.checkСount
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post


class PostViewHolder(
    private val binding: CardPostBinding,
    private val interactionListener: PostInteractionListener
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var post: Post

    private val popupMenu by lazy {
        PopupMenu(itemView.context, binding.menu).apply {
            inflate(R.menu.options_post)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.remove -> {
                        interactionListener.onRemove(post)
                        true
                    }
                    R.id.edit -> {
                        interactionListener.onEdit(post)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    init {
        with(binding) {
            like.setOnClickListener {
                interactionListener.onLike(post)
            }
            share.setOnClickListener {
                interactionListener.onShare(post)
            }
            menu.setOnClickListener {
                popupMenu.show()
            }
        }
    }

    fun bind(post: Post) {
        this.post = post

        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.text = post.likes.toString()
            like.isChecked = post.likedByMe
            share.text = checkСount(post.shared)
        }
    }
}
