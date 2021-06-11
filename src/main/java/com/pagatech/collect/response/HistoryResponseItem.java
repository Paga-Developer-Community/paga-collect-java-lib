package com.pagatech.collect.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class HistoryResponseItem implements Serializable {

    private String datetime;

    private String referenceNumber;

    private String accountNumber;

    private String operation;

    private String action;

    private Double amount;

    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
}
