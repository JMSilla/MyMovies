package com.kaelia.mymovies.moviesapi;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class ApiRequest {
    private static final String BASE_API_URL = "http://api.themoviedb.org/3/discover/movie";
    private static final String SORT_PARAM = "sort_by";
    private static final String API_PARAM = "api_key";
    private static final String REQUEST_METHOD = "GET";

    private String apiKey;
    private String sortType;
    private HttpURLConnection connection;

    ApiRequest(String apiKey, String sortType) {
        this.apiKey = apiKey;
        this.sortType = sortType;
    }

    void doRequest() throws IOException {
        URL url = getUrlForMostPopular();
        connection = (HttpURLConnection) url.openConnection();
        doHttpGetRequest();
    }

    private URL getUrlForMostPopular() throws MalformedURLException {
        Uri queryUri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(SORT_PARAM, sortType)
                .appendQueryParameter(API_PARAM, apiKey)
                .build();
        return new URL(queryUri.toString());
    }

    private void doHttpGetRequest() throws IOException {
        connection.setRequestMethod(REQUEST_METHOD);
        connection.connect();
    }

    String getResponse() throws IOException {
        BufferedReader responseReader = new BufferedReader(
                new InputStreamReader((connection.getInputStream())));

        StringBuilder jsonResponse = new StringBuilder();
        String responseLine;

        while ((responseLine = responseReader.readLine()) != null)
            jsonResponse.append(responseLine).append("\n");

        return jsonResponse.toString();
    }

    void disconnect() {
        connection.disconnect();
    }
}
