package com.georgeisaev.springnativebitly.controller.v1;

import com.georgeisaev.springnativebitly.data.dto.UrlDto;
import com.georgeisaev.springnativebitly.service.ShortenUrlService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/api/v1/urls")
public class ShortenUrlController {

    ShortenUrlService urlService;

    @PostMapping("/{url}")
    public Mono<ResponseEntity<UrlDto>> shortenUrl(@NotNull @PathVariable String url) {
        return Mono.fromCallable(() -> ResponseEntity.ok(urlService.shortenUrl(url)))
                .log();
    }


    @GetMapping(value = "/{key}")
    @ResponseBody
    public Mono<ResponseEntity<String>> findUrlByKey(@PathVariable String key) {
        return Mono.fromCallable(() -> urlService.findUrlByKey(key))
                .flatMap(Mono::justOrEmpty)
                .map(ResponseEntity::ok);
    }

}
