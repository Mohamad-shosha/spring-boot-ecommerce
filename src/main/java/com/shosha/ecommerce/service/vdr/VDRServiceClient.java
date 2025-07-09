package com.shosha.ecommerce.service.vdr;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.util.LinkedHashMap;

@FeignClient(
        name = "vdrService",
        url  = "${vdr.base-url}",
        fallback = VDRServiceFallback.class
)
public interface VDRServiceClient {

    @GetMapping("/")
    LinkedHashMap<String, String> check();

    @PostMapping(value = "/generate",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    ResponseEntity<Resource> generate(
            @RequestPart("user") MultipartFile person,
            @RequestPart("cloth") MultipartFile cloth
    );

}
