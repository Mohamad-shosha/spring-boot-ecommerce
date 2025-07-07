package com.shosha.ecommerce.service.vdr;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;

public class VDRServiceFallback implements VDRServiceClient {
    @Override
    public ResponseEntity<Resource> generate(MultipartFile person, MultipartFile cloth) {
        return null;
    }

    @Override
    public LinkedHashMap<String, String> check() {
        return null;
    }
}
