package com.pagatech.collect.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class DeletePersistentPaymentAccountResponse {
    @NotNull
    private String referenceNumber;
    @NotNull
    private String statusCode;
    private String statusMessage;
}
