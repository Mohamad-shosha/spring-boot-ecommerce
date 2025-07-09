package com.shosha.ecommerce.controller;

import com.shosha.ecommerce.service.vdr.VDRServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api/vdr")
public class VirtualDressingRoomController {
    private final Logger log = LoggerFactory.getLogger(VirtualDressingRoomController.class);
    private final VDRServiceClient vdrServiceClient;

    public VirtualDressingRoomController(VDRServiceClient vdrServiceClient) {
        this.vdrServiceClient = vdrServiceClient;
    }

    @GetMapping("/")
    public LinkedHashMap<String, String> checkIntegration() {
        return vdrServiceClient.check();
    }

    @PostMapping("/try-now")
    public ResponseEntity<Resource> tryNow(
            @RequestPart("user") MultipartFile person,
            @RequestPart("cloth") MultipartFile cloth) {
        log.debug("Request to try vdr");
        return vdrServiceClient.generate(person, cloth);
    }
}
