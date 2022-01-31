package com.georgeisaev.springnativebitly.service;

import com.georgeisaev.springnativebitly.data.dto.UrlDto;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public interface ShortenUrlService {

    Optional<String> findUrlByKey(@NotBlank String key);

    UrlDto shortenUrl(@NotBlank String url);

}
