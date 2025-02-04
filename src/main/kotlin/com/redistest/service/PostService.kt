package com.redistest.service

import com.redistest.dto.request.PostRequestDto
import com.redistest.dto.response.PostResponseDto
import com.redistest.entity.Post
import com.redistest.repository.PostRepository
import com.redistest.repository.RedisRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    private val postRepository: PostRepository,
    private val redisService: RedisService,
) {

    @Transactional
    fun createPost(postRequestDto: PostRequestDto): Unit {
        PostRequestDto.toEntity(postRequestDto).let { post ->
            postRepository.save(post)
        }
    }

    fun getPost(postId: Long): PostResponseDto {
        val postEntity: Post = postRepository.findById(postId).orElseThrow { throw IllegalArgumentException("Post $postId Not Found") }

        val responseDto: PostResponseDto = PostResponseDto.toDto(postEntity)

        redisService.incrementViewCount(postId)

        return responseDto

    }
}