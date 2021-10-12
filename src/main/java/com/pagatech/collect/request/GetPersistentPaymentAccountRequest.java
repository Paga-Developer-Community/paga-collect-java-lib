package com.pagatech.collect.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class GetPersistentPaymentAccountRequest {
    private String referenceNumber;
    private String accountIdentifier;
}
