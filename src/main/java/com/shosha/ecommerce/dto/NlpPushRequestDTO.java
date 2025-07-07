package com.shosha.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class NlpPushRequestDTO implements Serializable {

    public NlpPushRequestDTO() {
    }

    public NlpPushRequestDTO(Integer doReset) {
        this.doReset = doReset;
    }

    @JsonProperty("do_reset")
    private Integer doReset;

    public Integer getDoReset() {
        return doReset;
    }

    public void setDoReset(Integer doReset) {
        this.doReset = doReset;
    }
}
