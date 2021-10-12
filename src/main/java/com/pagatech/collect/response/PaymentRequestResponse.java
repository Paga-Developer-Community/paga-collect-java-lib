package com.pagatech.collect.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper=false)
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentRequestResponse  {

    /*
        The payment amount requested
     */
    Double requestAmount;

    /*
        The total payment amount including fees
     */
    Double totalPaymentAmount;

    String currency;

    /*
        The set of payment method being made available for this payment along
        with any payment method details for each one
     */
    private List<PaymentMethod> paymentMethods;
    /*
        The final expiry time for this payment request
     */
    private String expiryDateTimeUTC;
    /*
        True if the Payer details provide belong to a Paga Account holder
     */
    private Boolean isPayerPagaAccountHolder;

    private String note;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    private String referenceNumber;

    @NotNull
    private String statusCode;
    private String statusMessage;

    @Data
    @AllArgsConstructor
    public static final class PaymentMethod implements Serializable {

        private String name;
        private Map<String,Object> properties;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PaymentMethod that = (PaymentMethod) o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
