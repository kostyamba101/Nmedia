package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val published: String,
    val content: String,
    val likes: Long = 0,
    val likedByMe: Boolean = false,
    val shared: Long = 0
)