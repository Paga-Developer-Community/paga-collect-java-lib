package com.paga.api.library.collect.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    private String referenceNumber;
    @NotNull
    private String statusCode;
    private String statusMessage;

}
