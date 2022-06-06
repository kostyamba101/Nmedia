package ru.netology.nmedia.dto

data class Post(
    val id: Int,
    val author: String,
    val published: String,
    val content: String,
    var likes: Long = 0,
    var likedByMe: Boolean = false,
    var shared: Long = 0
)