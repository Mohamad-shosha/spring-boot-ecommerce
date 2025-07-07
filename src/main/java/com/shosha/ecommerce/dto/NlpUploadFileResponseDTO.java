package com.shosha.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class NlpUploadFileResponseDTO implements Serializable {

    private String signal;

    @JsonProperty("file_id")
    private String fileId;

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
