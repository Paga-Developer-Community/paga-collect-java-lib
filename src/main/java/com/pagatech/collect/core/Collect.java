package com.pagatech.collect.core;

import com.google.gson.Gson;
import com.pagatech.collect.request.*;
import com.pagatech.collect.response.*;
import com.pagatech.collect.util.Constants;
import com.pagatech.collect.util.Environment;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Collect {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String REFERENCE_NUMBER = "referenceNumber";

    private ApiConnection apiConnection;

    private final String principal;
    private final String apiKey;
    private final String credential;
    private final boolean test;
    private final String environment;

    public Collect(String principal, String apiKey, String credential, boolean test, String environment) {
        this.principal = principal;
        this.apiKey = apiKey;
        this.credential = credential;
        this.test = test;
        this.environment = environment;
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

            if(registerPersistentPaymentAccountRequest.getCreditBankId() != null)
                requestParams.put("creditBankId", registerPersistentPaymentAccountRequest.getCreditBankId());

            if(registerPersistentPaymentAccountRequest.getCreditBankAccountNumber() != null)
                requestParams.put("creditBankAccountNumber", registerPersistentPaymentAccountRequest.getCreditBankAccountNumber());

            if(registerPersistentPaymentAccountRequest.getCallbackUrl() != null)
                requestParams.put("callbackUrl", registerPersistentPaymentAccountRequest.getCallbackUrl());

        }catch (JSONException jsonException){
            jsonException.printStackTrace();
        }

        StringBuilder hashParams = new StringBuilder();
        hashParams.append(registerPersistentPaymentAccountRequest.getReferenceNumber());
        hashParams.append(registerPersistentPaymentAccountRequest.getAccountReference());
        hashParams.append(registerPersistentPaymentAccountRequest.getFinancialIdentificationNumber());

        if(registerPersistentPaymentAccountRequest.getCreditBankId() != null)
            hashParams.append(registerPersistentPaymentAccountRequest.getCreditBankId());

        if(registerPersistentPaymentAccountRequest.getCreditBankAccountNumber() != null)
            hashParams.append(registerPersistentPaymentAccountRequest.getCreditBankAccountNumber());

        if(registerPersistentPaymentAccountRequest.getCallbackUrl() != null)
            hashParams.append(registerPersistentPaymentAccountRequest.getCallbackUrl());

        hashParams.append(this.apiKey);

        RequestBody requestBody = RequestBody.create(JSON, requestParams.toString());

        JSONObject response = getApiResponse(requestBody, hashParams,
                getBaseUrl(this.test,this.environment) + Constants.REGISTER_PERSISTENT_PAYMENT_ACCOUNT);

        return gson.fromJson(String.valueOf(response), RegisterPersistentPaymentAccountResponse.class);
    }

    /**
     * Register a new request for payment between a payer and a payee
     * @param paymentRequestRequest
     * @return PaymentRequestResponse
     */
    public PaymentRequestResponse paymentRequest(PaymentRequestRequest paymentRequestRequest){

        Gson gson = new Gson();
        Map<String, Object> requestParams = new HashMap<>();
        JSONObject payer = new JSONObject();
        JSONObject payee = new JSONObject();

        try {

            if(paymentRequestRequest.getPayer().getName() != null)
                payer.put("name", paymentRequestRequest.getPayer().getName());

            if(paymentRequestRequest.getPayer().getPhoneNumber() != null)
                payer.put("phoneNumber", paymentRequestRequest.getPayer().getPhoneNumber());

            if(paymentRequestRequest.getPayer().getBankId() != null)
                payer.put("bankId", paymentRequestRequest.getPayer().getBankId());

            if(paymentRequestRequest.getPayer().getEmail() != null)
                payer.put("email", paymentRequestRequest.getPayer().getEmail());

            if(paymentRequestRequest.getPayee().getName() != null)
                payee.put("name", paymentRequestRequest.getPayee().getName());

            if(paymentRequestRequest.getPayee().getAccountNumber() != null)
                payee.put("accountNumber", paymentRequestRequest.getPayee().getAccountNumber());

            if(paymentRequestRequest.getPayee().getPhoneNumber() != null)
                payee.put("phoneNumber", paymentRequestRequest.getPayee().getPhoneNumber());

            if(paymentRequestRequest.getPayee().getBankId() != null)
                payee.put("bankId", paymentRequestRequest.getPayee().getBankId());

            if(paymentRequestRequest.getPayee().getBankAccountNumber() != null)
                payee.put("bankAccountNumber", paymentRequestRequest.getPayee().getBankAccountNumber());

            if(paymentRequestRequest.getPayee().getFinancialIdentificationNumber() != null)
                payee.put("financialIdentificationNumber", paymentRequestRequest.getPayee().getFinancialIdentificationNumber());

            requestParams.put(REFERENCE_NUMBER, paymentRequestRequest.getReferenceNumber());
            requestParams.put("amount", paymentRequestRequest.getAmount());
            requestParams.put("currency", paymentRequestRequest.getCurrency());
            requestParams.put("payer", payer);
            requestParams.put("payee", payee);
            requestParams.put("expiryDateTimeUTC", paymentRequestRequest.getExpiryDateTimeUTC());
            requestParams.put("isSuppressMessages", paymentRequestRequest.getIsSuppressMessages());
            requestParams.put("payerCollectionFeeShare", paymentRequestRequest.getPayerCollectionFeeShare());
            requestParams.put("payeeCollectionFeeShare", paymentRequestRequest.getPayeeCollectionFeeShare());
            requestParams.put("isAllowPartialPayments", paymentRequestRequest.getIsAllowPartialPayments());
            requestParams.put("paymentMethods", paymentRequestRequest.getPaymentMethods());
            requestParams.put("callBackUrl", paymentRequestRequest.getCallBackUrl());

        }catch (JSONException jsonException){
            jsonException.printStackTrace();
        }
        JSONObject JSONRequestParams = new JSONObject(requestParams);

        StringBuilder hashParams = new StringBuilder();
        hashParams.append(paymentRequestRequest.getReferenceNumber());
        hashParams.append(paymentRequestRequest.getAmount());

        if(paymentRequestRequest.getCurrency() != null)
            hashParams.append(paymentRequestRequest.getCurrency());

        if(paymentRequestRequest.getPayer().getPhoneNumber() != null)
            hashParams.append(paymentRequestRequest.getPayer().getPhoneNumber());

        if(paymentRequestRequest.getPayer().getEmail() != null)
            hashParams.append(paymentRequestRequest.getPayer().getEmail());

        if(paymentRequestRequest.getPayee().getAccountNumber() != null)
            hashParams.append(paymentRequestRequest.getPayee().getAccountNumber());

        if(paymentRequestRequest.getPayee().getPhoneNumber() != null)
            hashParams.append(paymentRequestRequest.getPayee().getPhoneNumber());

        if(paymentRequestRequest.getPayee().getBankId() != null)
            hashParams.append(paymentRequestRequest.getPayee().getBankId());

        if(paymentRequestRequest.getPayee().getBankAccountNumber() != null)
            hashParams.append(paymentRequestRequest.getPayee().getBankAccountNumber());

        hashParams.append(this.apiKey);

        RequestBody requestBody = RequestBody.create(JSON, JSONRequestParams.toString());

        JSONObject response = getApiResponse(requestBody, hashParams,
                getBaseUrl(this.test,this.environment) + Constants.PAYMENT_REQUEST);
        return gson.fromJson(String.valueOf(response), PaymentRequestResponse.class);
    }

    /**
     * Get all banks on Paga's platform
     * @param banksRequest
     * @return BankResponse Object
     */
    public BanksResponse getBanks(BanksRequest banksRequest){

        Gson gson = new Gson();
        JSONObject requestParams = new JSONObject();

        try{
            requestParams.put(REFERENCE_NUMBER, banksRequest.getReferenceNumber());

        }catch (JSONException jsonException){
            jsonException.printStackTrace();
        }

        StringBuilder hashParams = new StringBuilder();
        hashParams.append(banksRequest.getReferenceNumber());
        hashParams.append(this.apiKey);

        RequestBody requestBody = RequestBody.create(JSON, requestParams.toString());

        JSONObject response = getApiResponse(requestBody, hashParams,
                getBaseUrl(this.test, this.environment) + Constants.GET_BANKS);
        return gson.fromJson(String.valueOf(response), BanksResponse.class);
    }

    /**
     * Gets the status of a particular transaction
     * @param statusRequest
     * @return StatusResponse Object
     */
    public StatusResponse getStatus(StatusRequest statusRequest){

        Gson gson = new Gson();
        JSONObject requestParams = new JSONObject();

        try{
            requestParams.put(REFERENCE_NUMBER, statusRequest.getReferenceNumber());

        }catch (JSONException jsonException){
            jsonException.printStackTrace();
        }

        StringBuilder hashParams = new StringBuilder();
        hashParams.append(statusRequest.getReferenceNumber());
        hashParams.append(this.apiKey);

        RequestBody requestBody = RequestBody.create(JSON, requestParams.toString());

        JSONObject response = getApiResponse(requestBody, hashParams,
                getBaseUrl(this.test, this.environment) + Constants.GET_STATUS);


        return gson.fromJson(String.valueOf(response), StatusResponse.class);
    }


    /**
     * Get history of all activities for a period between to give start and end dates
     * @param historyRequest
     * @return
     */
    public HistoryResponse getHistory(HistoryRequest historyRequest){

        Gson gson = new Gson();
        JSONObject requestParams = new JSONObject();

        try{

            requestParams.put(REFERENCE_NUMBER, historyRequest.getReferenceNumber());
            requestParams.put("startDateTimeUTC", historyRequest.getStartDateTimeUTC());
            requestParams.put("endDateTimeUTC", historyRequest.getEndDateTimeUTC());

        }catch (JSONException jsonException){
            jsonException.printStackTrace();
        }

        StringBuilder hashParams = new StringBuilder();
        if(historyRequest.getReferenceNumber() != null)
            hashParams.append(historyRequest.getReferenceNumber());
        hashParams.append(this.apiKey);

        RequestBody requestBody = RequestBody.create(JSON, requestParams.toString());

        JSONObject response = getApiResponse(requestBody, hashParams,
                getBaseUrl(this.test, this.environment) + Constants.GET_HISTORY);

        return gson.fromJson(String.valueOf(response), HistoryResponse.class);
    }

    /**
     * Query the properties associated with an existing persistent payment account
     * @param getPersistentPaymentAccountRequest
     * @return
     */
    public GetPersistentPaymentAccountResponse getPersistentPaymentAccount(GetPersistentPaymentAccountRequest getPersistentPaymentAccountRequest) {
        Gson gson = new Gson();
        JSONObject requestParams = new JSONObject();

        try{
            requestParams.put(REFERENCE_NUMBER, getPersistentPaymentAccountRequest.getReferenceNumber());
            requestParams.put("accountIdentifier", getPersistentPaymentAccountRequest.getAccountIdentifier());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringBuilder hashParams = new StringBuilder();
        hashParams.append(getPersistentPaymentAccountRequest.getReferenceNumber());
        hashParams.append(getPersistentPaymentAccountRequest.getAccountIdentifier());
        hashParams.append(this.apiKey);
        RequestBody requestBody = RequestBody.create(JSON, requestParams.toString());
        JSONObject response = getApiResponse(requestBody,hashParams,getBaseUrl(this.test, this.environment) + Constants.GET_PERSISTENT_PAYMENT_ACCOUNT);
        return gson.fromJson(String.valueOf(response), GetPersistentPaymentAccountResponse.class);
    }

    /**
     * This endpoint allows for deleting a persistent payment account.
     * @param deletePersistentPaymentAccountRequest
     * @return
     */
    public DeletePersistentPaymentAccountResponse deletePersistentPaymentAccount(DeletePersistentPaymentAccountRequest deletePersistentPaymentAccountRequest) {
        Gson gson = new Gson();
        JSONObject requestParams = new JSONObject();

        try{
            requestParams.put(REFERENCE_NUMBER, deletePersistentPaymentAccountRequest.getReferenceNumber());
            requestParams.put("accountIdentifier", deletePersistentPaymentAccountRequest.getAccountIdentifier());
            requestParams.put("reason", deletePersistentPaymentAccountRequest.getReason());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringBuilder hashParams = new StringBuilder();
        hashParams.append(deletePersistentPaymentAccountRequest.getReferenceNumber());
        hashParams.append(deletePersistentPaymentAccountRequest.getAccountIdentifier());
        hashParams.append(this.apiKey);
        RequestBody requestBody = RequestBody.create(JSON, requestParams.toString());
        JSONObject response = getApiResponse(requestBody,hashParams,getBaseUrl(this.test, this.environment) + Constants.DELETE_PERSISTENT_PAYMENT_ACCOUNT);
        return gson.fromJson(String.valueOf(response), DeletePersistentPaymentAccountResponse.class);
    }

    /**
     * This end-point can be used to either cancel or initiate a refund if we were unable to fulfill the request for one reason or the other
     * @param refundPaymentRequest
     * @return
     */
    public RefundPaymentResponse refund(RefundPaymentRequest refundPaymentRequest) {
        Gson gson = new Gson();
        JSONObject requestParams = new JSONObject();

        try{
            requestParams.put(REFERENCE_NUMBER, refundPaymentRequest.getReferenceNumber());
            requestParams.put("refundAmount", refundPaymentRequest.getRefundAmount());
            requestParams.put("reason", refundPaymentRequest.getReason());
            requestParams.put("currency", refundPaymentRequest.getCurrency());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringBuilder hashParams = new StringBuilder();
        hashParams.append(refundPaymentRequest.getReferenceNumber());
        hashParams.append(refundPaymentRequest.getRefundAmount());
        hashParams.append(this.apiKey);
        RequestBody requestBody = RequestBody.create(JSON, requestParams.toString());
        JSONObject response = getApiResponse(requestBody,hashParams,getBaseUrl(this.test, this.environment) + Constants.REFUND_PAYMENT_REQUEST);
        return gson.fromJson(String.valueOf(response), RefundPaymentResponse.class);
    }


    /**
     * This endpoint allows for changing any of the account properties except the accountNumber (NUBAN) and the accounReference properties which cannot be changed
     * @param updatePersistentPaymentAccountRequest
     * @return
     */
    public UpdatePersistentPaymentAccountResponse updatePersistentPaymentAccount(UpdatePersistentPaymentAccountRequest updatePersistentPaymentAccountRequest) {
        Gson gson = new Gson();
        JSONObject requestParams = new JSONObject();
        StringBuilder hashParams = new StringBuilder();

        try{
            requestParams.put(REFERENCE_NUMBER, updatePersistentPaymentAccountRequest.getReferenceNumber());
            hashParams.append(updatePersistentPaymentAccountRequest.getReferenceNumber());

            requestParams.put("accountIdentifier", updatePersistentPaymentAccountRequest.getAccountIdentifier());
            hashParams.append(updatePersistentPaymentAccountRequest.getAccountIdentifier());

            if(updatePersistentPaymentAccountRequest.getPhoneNumber() != null) {
                requestParams.put("phoneNumber", updatePersistentPaymentAccountRequest.getPhoneNumber());
            }

            if(updatePersistentPaymentAccountRequest.getFirstName() != null) {
                requestParams.put("firstName", updatePersistentPaymentAccountRequest.getFirstName());
            }



            if(updatePersistentPaymentAccountRequest.getLastName() != null) {
                requestParams.put("lastName", updatePersistentPaymentAccountRequest.getLastName());
            }


            if(updatePersistentPaymentAccountRequest.getAccountName() != null) {
                requestParams.put("accountName", updatePersistentPaymentAccountRequest.getAccountName());
            }

            if(updatePersistentPaymentAccountRequest.getCreditBankId() != null) {
                requestParams.put("creditBankId", updatePersistentPaymentAccountRequest.getCreditBankId());
                hashParams.append(updatePersistentPaymentAccountRequest.getCreditBankId());
            }


            if(updatePersistentPaymentAccountRequest.getFinancialIdentificationNumber() != null) {
                requestParams.put("financialIdentificationNumber", updatePersistentPaymentAccountRequest.getFinancialIdentificationNumber());
                hashParams.append(updatePersistentPaymentAccountRequest.getFinancialIdentificationNumber());
            }

            if(updatePersistentPaymentAccountRequest.getCreditBankAccountNumber() != null) {
                requestParams.put("creditBankAccountNumber", updatePersistentPaymentAccountRequest.getCreditBankAccountNumber());
                hashParams.append(updatePersistentPaymentAccountRequest.getCreditBankAccountNumber());
            }

            if(updatePersistentPaymentAccountRequest.getCallbackUrl() != null) {
                requestParams.put("callbackUrl", updatePersistentPaymentAccountRequest.getCallbackUrl());
                hashParams.append(updatePersistentPaymentAccountRequest.getCallbackUrl());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        hashParams.append(this.apiKey);
        RequestBody requestBody = RequestBody.create(JSON, requestParams.toString());
        JSONObject response = getApiResponse(requestBody,hashParams,getBaseUrl(this.test, this.environment) + Constants.UPDATE_PERSISTENT_PAYMENT_ACCOUNT);
        return gson.fromJson(String.valueOf(response), UpdatePersistentPaymentAccountResponse.class);
    }


    public static String getBaseUrl(boolean test, String environment){
        if(test && environment == null){
            return Constants.TEST_BASE_API_ENDPOINT;
        } else if (test && Environment.valueOf(environment.toLowerCase())== Environment.qa1) {
            return Constants.QA1_BASE_API_ENDPOINT;
        }
        else{
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
        private String environment;


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

        public Builder setEnvironment(String environment) {
            this.environment = environment;
            return  this;
        }


        public Collect build() {
            return new Collect(principal, apiKey, credential, test, environment);
        }
    }
}
