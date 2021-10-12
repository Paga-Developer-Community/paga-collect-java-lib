package com.pagatech.collect.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class UpdatePersistentPaymentAccountRequest {
    private String referenceNumber;
    private String accountIdentifier;
    private String accountName;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String financialIdentificationNumber;
    private String creditBankId;
    private String creditBankAccountNumber;
    private String callbackUrl;
}
