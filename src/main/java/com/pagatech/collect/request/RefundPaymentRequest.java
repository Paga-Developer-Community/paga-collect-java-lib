package com.pagatech.collect.request;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class RefundPaymentRequest {
    private String referenceNumber;
    private String refundAmount;
    private String currency;
    private String reason;

}
