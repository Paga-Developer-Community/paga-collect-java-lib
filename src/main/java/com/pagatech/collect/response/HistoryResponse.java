package com.pagatech.collect.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
public class HistoryResponse  {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    private String referenceNumber;
    @NotNull
    private String statusCode;
    private String statusMessage;

    private int itemCount;

    private List<HistoryResponseItem> items;
}
