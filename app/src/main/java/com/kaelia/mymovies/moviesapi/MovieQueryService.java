package com.kaelia.mymovies.moviesapi;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MovieQueryService {

    public static List<MovieInfo> getMostPopularMovies() {
        List<MovieInfo> movieInfoList = new ArrayList<>();

        final String BASE_API_URL = "http://api.themoviedb.org/3/discover/movie";
        final String SORT_PARAM = "sort_by";
        final String SORT_BY_POPULARITY_DESC = "popularity.desc";
        final String API_PARAM = "api_key";
        final String API_KEY = "";

        Uri queryUri = Uri.parse(BASE_API_URL).buildUpon()
                .appendQueryParameter(SORT_PARAM, SORT_BY_POPULARITY_DESC)
                .appendQueryParameter(API_PARAM, API_KEY)
                .build();

        try {
            URL url = new URL(queryUri.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader responseReader = new BufferedReader(
                    new InputStreamReader((connection.getInputStream())));

            StringBuilder jsonResponse = new StringBuilder();
            String responseLine;

            while ((responseLine = responseReader.readLine()) != null)
                jsonResponse.append(responseLine).append("\n");

            // JSON parsing
            JSONObject mainJsonObject = new JSONObject(jsonResponse.toString());

            JSONArray resultsArray = mainJsonObject.getJSONArray("results");

            for(int i = 0; i < resultsArray.length(); i++) {
                MovieInfo movieInfo = new MovieInfo();
                JSONObject movieResult = resultsArray.getJSONObject(i);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                movieInfo.setOriginalTitle(movieResult.getString("original_title"));
                movieInfo.setMoviePosterImageThumbnail(movieResult.getString("backdrop_path"));
                movieInfo.setSynopsis(movieResult.getString("overview"));
                movieInfo.setRating(new BigDecimal(movieResult.getString("vote_average")));
                movieInfo.setReleaseDate(dateFormat.parse(movieResult.getString("release_date")));

                movieInfoList.add(movieInfo);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return movieInfoList;
    }
}
