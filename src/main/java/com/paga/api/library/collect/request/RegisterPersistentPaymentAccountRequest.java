package com.paga.api.library.collect.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class RegisterPersistentPaymentAccountRequest {

    private String referenceNumber;
    private String accountReference;
    private String accountName;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String financialIdentificationNumber;
    private String creditBankId;
    private String creditBankAccountNumber;
    private String callbackUrl;

}
