package com.pagatech.collect.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class BanksResponse {

    List<Bank> banks;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    private String referenceNumber;
    @NotNull
    private String statusCode;
    private String statusMessage;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    public static class Bank implements Serializable {
        private String name;
        private String uuid;
        private String interInstitutionCode;
        private String sortCode;
        private String ussdCode;
    }
}