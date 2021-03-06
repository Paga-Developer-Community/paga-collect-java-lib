package com.pagatech.collect.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
@NoArgsConstructor
public class StatusResponse  {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    private String referenceNumber;
    @NotNull
    private String statusCode;
    private String statusMessage;
    @NotNull
    Response data;
}
