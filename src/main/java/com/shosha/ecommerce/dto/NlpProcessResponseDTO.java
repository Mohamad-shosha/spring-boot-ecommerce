package com.shosha.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class NlpProcessResponseDTO implements Serializable {

    private String signal;

    @JsonProperty("inserted_chunks")
    private Long insertedChunks;

    @JsonProperty("processed_files")
    private Integer processedFiles;

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public Long getInsertedChunks() {
        return insertedChunks;
    }

    public void setInsertedChunks(Long insertedChunks) {
        this.insertedChunks = insertedChunks;
    }

    public Integer getProcessedFiles() {
        return processedFiles;
    }

    public void setProcessedFiles(Integer processedFiles) {
        this.processedFiles = processedFiles;
    }
}
