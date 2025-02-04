package com.redistest.dto.response

import com.redistest.entity.Post

data class PostResponseDto(
    val id: Long?,
    val title: String,
    val content: String,
    val viewCount: Long? = 0,
) {

    // 정적 팩토리 메서드
    companion object {
        fun toDto(post: Post): PostResponseDto {
            return PostResponseDto(
                id = post.id,
                title = post.title,
                content = post.content,
                viewCount = post.viewCount,
            )
        }
    }
}
