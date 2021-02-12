package com.pranavkapoorr.multipay.wpos.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Pranav
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IpsJson {
    @JsonProperty
    private String printFlag ;
    @JsonProperty
    private String operationType ;
    @JsonProperty
    private String amount;
    @JsonProperty
    private String pedIp;
    @JsonProperty
    private String pedPort;
    @JsonProperty
    private String statusMessageIp;
    @JsonProperty
    private String GTbit;
    @JsonProperty
    private String statusMessagePort;
    @JsonProperty
    private String transactionReference;
    @JsonProperty
    private String timeOut;
    @JsonProperty
    private String wait4CardRemoved;
}
