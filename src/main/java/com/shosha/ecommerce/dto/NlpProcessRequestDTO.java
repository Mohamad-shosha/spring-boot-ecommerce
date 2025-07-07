package com.shosha.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class NlpProcessRequestDTO implements Serializable {

    @JsonProperty("chunk_size")
    private Integer chunkSize;

    @JsonProperty("overlap_size")
    private Integer overlapSize;

    @JsonProperty("do_reset")
    private Integer doReset;

    public NlpProcessRequestDTO() {
    }

    public NlpProcessRequestDTO(Integer chunkSize,
                                Integer overlapSize,
                                Integer doReset) {
        this.chunkSize = chunkSize;
        this.overlapSize = overlapSize;
        this.doReset = doReset;
    }

    public Integer getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(Integer chunkSize) {
        this.chunkSize = chunkSize;
    }

    public Integer getOverlapSize() {
        return overlapSize;
    }

    public void setOverlapSize(Integer overlapSize) {
        this.overlapSize = overlapSize;
    }

    public Integer getDoReset() {
        return doReset;
    }

    public void setDoReset(Integer doReset) {
        this.doReset = doReset;
    }
}
