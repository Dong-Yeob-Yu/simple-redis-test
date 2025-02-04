package com.redistest.dto.request

import com.redistest.entity.Post

data class PostRequestDto(
    val title: String,
    val content: String,
) {
    companion object {
        fun toEntity(postRequestDto: PostRequestDto): Post {
            return Post(
                id = null,
                title = postRequestDto.title,
                content = postRequestDto.content,
                viewCount = null
            )
        }
    }
}
