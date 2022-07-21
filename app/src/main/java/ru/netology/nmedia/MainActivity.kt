package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import ru.netology.nmedia.adapter.PostAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.hideKeyboard
import ru.netology.nmedia.viewmodel.PostViewModel
import java.math.RoundingMode
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(viewModel)
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        viewModel.editPost.observe(this) { post: Post? ->
            val content = post?.content ?: ""
            binding.groupEditContentMessage.isVisible = post != null
            binding.contentEditText.setText(content)
            binding.editContentPreview.setText(content)
        }
        with(binding) {
            saveButton.setOnClickListener {
                with(binding.contentEditText) {
                    val content = text.toString()
                    viewModel.onSaveButtonClicked(content)
                    //groupEditContentMessage.visibility = View.GONE
                    clearFocus()
                    hideKeyboard()
                }
            }
            cancelButton.setOnClickListener {
                with(binding.contentEditText) {
                    viewModel.onCancelButtonClicked()
                    //groupEditContentMessage.visibility = View.GONE
                    clearFocus()
                    hideKeyboard()
                }
            }
            contentEditText.setOnClickListener {
                //groupEditContentMessage.visibility = View.VISIBLE
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
