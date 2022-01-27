package com.georgeisaev.springnativebitly.service;

import com.georgeisaev.springnativebitly.data.dto.UrlDto;

import javax.validation.constraints.NotBlank;

public interface ShortenUrlService {

    String findUrlByKey(@NotBlank String key);

    UrlDto shortenUrl(@NotBlank String url);

}
