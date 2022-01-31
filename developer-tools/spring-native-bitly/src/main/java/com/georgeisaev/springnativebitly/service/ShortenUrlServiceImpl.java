package com.georgeisaev.springnativebitly.service;

import com.georgeisaev.springnativebitly.data.dto.UrlDto;
import com.google.common.hash.Hashing;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ShortenUrlServiceImpl implements ShortenUrlService {

    RedisTemplate<String, UrlDto> redisUrlTemplate;

    @Override
    public Optional<String> findUrlByKey(String key) {
        var url = redisUrlTemplate.opsForValue().get(key);
        return Optional.ofNullable(url).map(UrlDto::getUrl);
    }

    @Override
    public UrlDto shortenUrl(String url) {
        String key = Hashing.murmur3_32_fixed().hashString(url, Charset.defaultCharset()).toString();
        var shortUrlEntry = UrlDto.builder().key(key).createdAt(LocalDateTime.now()).url(url).build();
        redisUrlTemplate.opsForValue().set(key, shortUrlEntry, 36000L, TimeUnit.SECONDS);
        return shortUrlEntry;
    }

}
