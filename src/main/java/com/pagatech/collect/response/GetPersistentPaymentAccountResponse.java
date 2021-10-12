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
public class GetPersistentPaymentAccountResponse {
             @NotNull
             private String referenceNumber;
             @NotNull
             private String statusCode;
             private String statusMessage;
             private String accountReference;
             private String accountNumber;
             private String accountName;
             private String phoneNumber;
             private String firstName;
             private String lastName;
             private String financialIdentificationNumber;
             private String creditBankId;
             private String creditBankAccountNumber;
}
