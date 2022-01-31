package com.georgeisaev.springnativebitly.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.georgeisaev.springnativebitly.data.dto.UrlDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
public class RedisConfiguration {

    ObjectMapper objectMapper;
    RedisConnectionFactory connectionFactory;

    @Bean
    public RedisTemplate<String, UrlDto> redisUrlTemplate() {
        var redisTemplate = new RedisTemplate<String, UrlDto>();
        var valueSerializer = new Jackson2JsonRedisSerializer<UrlDto>(UrlDto.class);
        valueSerializer.setObjectMapper(objectMapper);
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(valueSerializer);
        return redisTemplate;
    }

}
