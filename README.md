# Paga Collect Java library
The Paga Collect API allows anyone to initiate a payment request to a third party and automatically get notified when the payment request is fulfilled. This library makes it easier and faster for developers to integrate the API

### 1. Installation
Download the [jar](https://github.com/zubairAbubakar/paga-collect-java-lib/blob/main/src/main/resources/pagatech.zip) and install in your project

If you are using a build process such as Maven or Gradle, follow the process below,

Step 1. Extract and add the downloaded package to ~/.m2 directory
```shell
- path .m2/repository/com/pagatech/collect-lib/1.0.0/
```

Step 2. Add the dependency to your pom.xml or build.gradle file.
<br>

### Maven
Go to your pom file and add the following to your pom.xml

Then add the Paga Business client dependency under your dependencies
```sh
<dependencies>
       <dependency>
            <groupId>com.pagatech</groupId>
            <artifactId>collect-lib</artifactId>
            <version>1.0.0</version>
       </dependency>
</dependencies>
```
<br>

### Gradle
Add the Paga Collect library dependency under your dependencies

```sh
//include mavenLocal( ) under your repositories
repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    compile(group: 'com.pagatech', name: 'collect-lib', version: '1.0.0')

}
```

<br>

### 2. Usage
Once installed to import the library
```sh
import <packageName>.Collect;

```

Initialize it, see sample code below:

```sh
Collect collect = new Collect.Builder()
        .setApiKey(hash_key)
        .setPrincipal(principal)
        .setCredential(credentials)
        .setTest(false)
        .build();
```

<br>

```sh
<strong>Note</strong>
Test Server can be true or false. True means you calling PAGA test server while False means you are calling PAGA live Server.
```

### Paga Collect API Operations
Now that you have created a collect object you easily call its operations

<br>

#### Request Payment
Register a new request for payment between a payer and a payee. Once a payment request is initiated successfully, the payer is notified by the platform (this can be suppressed) and can proceed to authorize/execute the payment. Once the payment is fulfilled, a notification is sent to the supplied callback URL. See the callback notification section for more details.
<br>
To make a payment request see sample code below:
```sh
PaymentRequestRequest.Payer payer = new PaymentRequestRequest.Payer();
payer.setName("John Doe");
payer.setPhoneNumber("07033333333");
payer.setBankId("3E94C4BC-6F9A-442F-8F1A-8214478D5D86");

PaymentRequestRequest.Payee payee = new PaymentRequestRequest.Payee();
payee.setName("Mary Doe");
payee.setAccountNumber("1111111111");
payee.setBankId("3E94C4BC-6F9A-442F-8F1A-8214478D5D86");
payee.setBankAccountNumber("000000000");
payee.setFinancialIdentificationNumber("02222843212");

List<String> paymentMethods = new ArrayList<>();
paymentMethods.add("BANK_TRANSFER");
paymentMethods.add("FUNDING_USSD");

PaymentRequestResponse paymentRequestResponse = collect.paymentRequest(PaymentRequestRequest.builder()
        .referenceNumber("6020000011z10aab2")
        .amount("200")
        .currency("NGN")
        .payer(payer)
        .payee(payee)
        .expiryDateTimeUTC("2021-06-30T00:00:00")
        .isSuppressMessages(true)
        .isAllowPartialPayments(true)
        .payerCollectionFeeShare(0.5)
        .payeeCollectionFeeShare(0.5)
        .callBackUrl("http://localhost:9091/test-callback")
        .paymentMethods(paymentMethods)
        .build());

System.out.println(paymentRequestResponse.toString());
```

<br>

#### Register Persistent Payment Account

An operation for business to create Persistent Payment Account Numbers that can be assigned to their customers for payment collection.
<br>
To create a persistent payment account see sample code below:
```sh
RegisterPersistentPaymentAccountResponse response = collect.registerPersistentPaymentAccount(
        RegisterPersistentPaymentAccountRequest.builder()
                .referenceNumber("test12345100zz0")
                .phoneNumber("07033333333")
                .accountName("John Doe")
                .firstName("John")
                .lastName("Doe")
                .accountReference("012111111111")
                .financialIdentificationNumber("22222222222")
                .creditBankId("3E94C4BC-6F9A-442F-8F1A-8214478D5D86")
                .creditBankAccountNumber("0000000000")
                .callbackUrl("http://localhost:9091/test-callback")
                .build());

System.out.println(response.toString());
```

<br>

#### Query Status
Query the current status of a submitted request
<br>
To check the status of a submitted request see sample code below:
```sh
StatusResponse statusResponse = collect.getStatus(StatusRequest.builder()
        .referenceNumber("6020000011z10aab2")
        .build());

System.out.println(statusResponse.toString());
```

<br>

#### Query History
Get payment requests for a period between given start and end dates. The period window should not exceed 1 month.
<br>
See sample code below:
```sh
HistoryResponse historyResponse = collect.getHistory(HistoryRequest.builder()
        .referenceNumber("6020000011z10aab2")
        .startDateTimeUTC("2021-05-30T00:00:00")
        .endDateTimeUTC("2021-06-10T00:00:00")
        .build());

System.out.println(historyResponse.toString());
```

<br>

#### Get Banks
Retrieve a list of supported banks and their complementary unique ids on the bank. This is required for populating the payer (optional) and payee objects in the payment request model.
<br>
See usage sample code below:
```sh
BanksResponse banks = collect.getBanks(BanksRequest.builder()
        .referenceNumber("test002")
        .build());

System.out.println(banks.toString());
```

