package com.pagatech.collect.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder
@NoArgsConstructor
public class PaymentRequestRequest extends Request {

    private String amount;
    @NonNull
    private String currency;
    @NonNull
    private Payer payer;
    private Payee payee;

    private String expiryDateTimeUTC;
    private Boolean isSuppressMessages;
    private double payerCollectionFeeShare;
    private double payeeCollectionFeeShare;

    private Boolean isAllowPartialPayments;
    private String callBackUrl;

    public List<String> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<String> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    private List<String> paymentMethods;
    private String note;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    public static final class Payer {

        private String name;
        private String phoneNumber;
        private String email;
        private String bankId;
        private String bankAccountNumber;
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    public static final class Payee {

        private String name;
        private String accountNumber;
        private String phoneNumber;
        private String bankId;
        private String bankAccountNumber;
        private String financialIdentificationNumber;
    }
}
