package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                countLikes.text = checkСount(post.likes)
                countShare.text = checkСount(post.shared)
                like.setImageResource(if (post.likedByMe) R.drawable.ic_baseline_favorite_red_24dp else R.drawable.ic_baseline_favorite_24dp)
            }
        }
        with(binding) {
            like.setOnClickListener {
                viewModel.like()
            }
            share.setOnClickListener {
                viewModel.share()
            }
        }
    }
}

fun checkСount(number: Long): String {
    val decimalFormat = DecimalFormat("#.#")
    decimalFormat.roundingMode = RoundingMode.DOWN
    return when (number) {
        in 0..999 -> "$number"
        in 1000..9_999 -> "${decimalFormat.format(number.toFloat() / 1_000)}K"
        in 10000..999_999 -> "${number / 1_000}K"
        in 1000000..9_999_999 -> "${decimalFormat.format(number.toFloat() / 1_000_000)}M"
        else -> "${number / 1_000_000}M"
    }
}