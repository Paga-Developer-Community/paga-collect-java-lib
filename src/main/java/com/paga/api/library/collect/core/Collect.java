package com.paga.api.library.collect.core;

import com.google.gson.Gson;
import com.paga.api.library.collect.request.*;
import com.paga.api.library.collect.response.*;
import com.paga.api.library.collect.util.Constants;;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Collect {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String REFERENCE_NUMBER = "referenceNumber";

    private ApiConnection apiConnection;

    private String principal;
    private String apiKey;
    private String credential;
    private boolean test;

    public Collect(String principal, String apiKey, String credential, boolean test) {
        this.principal = principal;
        this.apiKey = apiKey;
        this.credential = credential;
        this.test = test;
    }


    /**
     * An operation for business to create Persistent Payment Account Numbers that can be assigned to their customers
     * for payment collection.
     * @param registerPersistentPaymentAccountRequest
     * @return RegisterPersistentPaymentAccountResponse
     *
     */
    public RegisterPersistentPaymentAccountResponse registerPersistentPaymentAccount(RegisterPersistentPaymentAccountRequest
                                                                                             registerPersistentPaymentAccountRequest){
        Gson gson = new Gson();
        JSONObject requestParams = new JSONObject();

        try {
            requestParams.put(REFERENCE_NUMBER, registerPersistentPaymentAccountRequest.getReferenceNumber());
            requestParams.put("phoneNumber", registerPersistentPaymentAccountRequest.getPhoneNumber());
            requestParams.put("firstName", registerPersistentPaymentAccountRequest.getFirstName());
            requestParams.put("lastName", registerPersistentPaymentAccountRequest.getLastName());
            requestParams.put("accountName", registerPersistentPaymentAccountRequest.getAccountName());
            requestParams.put("financialIdentificationNumber", registerPersistentPaymentAccountRequest.getFinancialIdentificationNumber());
            requestParams.put("accountReference", registerPersistentPaymentAccountRequest.getAccountReference());
            requestParams.put("email", registerPersistentPaymentAccountRequest.getEmail());
            requestParams.put("creditBankId", registerPersistentPaymentAccountRequest.getCreditBankId());
            requestParams.put("creditBankAccountNumber", registerPersistentPaymentAccountRequest.getCreditBankAccountNumber());
            requestParams.put("callbackUrl", registerPersistentPaymentAccountRequest.getCallbackUrl());

            System.out.println("Persistent Account request object :: " + requestParams);
        }catch (JSONException jsonException){
            jsonException.printStackTrace();
        }

        StringBuilder hashParams = new StringBuilder();
        hashParams.append(registerPersistentPaymentAccountRequest.getReferenceNumber());
        hashParams.append(registerPersistentPaymentAccountRequest.getAccountReference());
        hashParams.append(registerPersistentPaymentAccountRequest.getFinancialIdentificationNumber());
        hashParams.append(registerPersistentPaymentAccountRequest.getCreditBankId());
        hashParams.append(registerPersistentPaymentAccountRequest.getCreditBankAccountNumber());
        hashParams.append(registerPersistentPaymentAccountRequest.getCallbackUrl());
        hashParams.append(this.apiKey);

        RequestBody requestBody = RequestBody.create(JSON, requestParams.toString());

        JSONObject response = getApiResponse(requestBody, hashParams,
                getBaseUrl(this.test) + Constants.REGISTER_PERSISTENT_PAYMENT_ACCOUNT);

        System.out.println("response :: " + response);
        return gson.fromJson(String.valueOf(response), RegisterPersistentPaymentAccountResponse.class);
    }





    public static String getBaseUrl(boolean test){
        if(test){
            return Constants.TEST_BASE_API_ENDPOINT;
        }else{
            return Constants.LIVE_BASE_API_ENDPOINT;
        }
    }

    private JSONObject getApiResponse(RequestBody body, StringBuilder sBuilder, String requestPath) {
        this.apiConnection = new ApiConnection();
        return this.apiConnection.connectAndPost(body, sBuilder, requestPath, this.principal, this.credential);
    }

    public static class Builder {
        private String principal;
        private String apiKey;
        private Boolean test;
        private String credential;


        public Builder() {
        }

        public Builder setCredential(String credential) {
            this.credential = credential;
            return this;
        }

        public Builder setPrincipal(String principal) {
            this.principal = principal;
            return this;
        }

        public Builder setTest(Boolean test) {
            this.test = test;
            return this;
        }

        public Builder setApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Collect build() {
            return new Collect(principal, apiKey, credential, test);
        }
    }
}
