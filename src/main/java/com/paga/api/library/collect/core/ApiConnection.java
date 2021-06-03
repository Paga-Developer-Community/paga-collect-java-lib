package com.paga.api.library.collect.core;

import com.paga.api.library.collect.util.UtilService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import org.codehaus.plexus.util.Base64;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;


public class ApiConnection {

    /**
     *
     * @param requestBody
     * @param pattern
     * @param requestPath
     * @param principal
     * @param credentials
     * @return
     */
    public JSONObject connectAndPost(RequestBody requestBody, StringBuilder pattern, String requestPath,
                                     String principal, String credentials) {
        UtilService utilService = new UtilService();
        String respString = "";
        JSONObject jsonObject = null;
        String hash = utilService.hashComputeHash(pattern);
        System.out.println(hash);

        Request request = buildRequest(requestPath, hash, requestBody, principal, credentials);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            respString = response.body().string().trim();
            System.out.println(response);

            jsonObject = new JSONObject(respString);

            System.out.println("::" + respString);

            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject = new JSONObject();
            System.err.println("exception :: " + e.getMessage());
            jsonObject.put("response", response.message());
            jsonObject.put("responseCode", response.code());
        } finally {
            return jsonObject;
        }

    }

    /**
     *
     * @param requestPath
     * @param hash
     * @param body
     * @param principal
     * @param credential
     * @return
     */
    private Request buildRequest(String requestPath, String hash, RequestBody body, String principal, String credential) {

        System.out.println("about to build request");
        return new Request.Builder()
                .url(requestPath)
                .header("Content-Type", "application/json")
                .header("Authorization", getBasicAuth(principal, credential))
                .header("hash", hash)
                .post(body)
                .build();

    }

    /**
     *
     * @param principal
     * @param credential
     * @return
     */
    private String getBasicAuth(String principal, String credential) {

        String auth = principal + ":" + credential;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );

        return authHeader;
    }


}
