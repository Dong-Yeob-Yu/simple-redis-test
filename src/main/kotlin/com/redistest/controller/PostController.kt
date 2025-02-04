package com.redistest.controller

import com.redistest.dto.request.PostRequestDto
import com.redistest.dto.response.PostResponseDto
import com.redistest.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class PostController(
    private val postService: PostService,
) {

    @GetMapping("/posts/{postId}")
    fun getPost(@PathVariable postId: Long): ResponseEntity<PostResponseDto> {
        return ResponseEntity.ok().body(postService.getPost(postId))
    }

    @PostMapping("/posts")
    fun createPost(@RequestBody postRequestDto: PostRequestDto): ResponseEntity<HttpStatus> {
        postService.createPost(postRequestDto)
        return ResponseEntity.ok(HttpStatus.CREATED)
    }
}