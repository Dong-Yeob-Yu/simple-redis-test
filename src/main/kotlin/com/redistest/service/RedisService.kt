package com.redistest.service

import org.apache.coyote.http11.Constants.a
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class RedisService(
    private val redisTemplate: RedisTemplate<String, String>
) {

    fun incrementViewCount(postId: Long){
        val increment: Long? = redisTemplate.opsForValue().increment("post:${postId}:count")

        // 처음 생성된 경우 TTL 5분
        if(increment == 1L) {
            redisTemplate.expire("post:${postId}:count", Duration.ofMinutes(5))
        }
    }

}