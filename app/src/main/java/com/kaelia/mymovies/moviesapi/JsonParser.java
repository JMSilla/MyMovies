package com.kaelia.mymovies.moviesapi;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

class JsonParser {
    private static final String IMAGES_BASE_URL = "http://image.tmdb.org/t/p/w185";
    private static final String RESULTS_ARRAY = "results";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String POSTER_PATH = "poster_path";
    private static final String SYNOPSIS = "overview";
    private static final String RATING = "vote_average";
    private static final String RELEASE_DATE = "release_date";
    private static final String JSON_NULL = "null";

    private static final String DATE_FORMAT_STRING = "yyyy-MM-dd";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STRING);

    private String jsonResponse;

    JsonParser(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    List<MovieInfo> getMovieInfoList()
            throws JSONException, ParseException
    {
        List<MovieInfo> movieInfoList = new ArrayList<>();
        JSONObject mainJsonObject = new JSONObject(jsonResponse);
        JSONArray resultsArray = mainJsonObject.getJSONArray(RESULTS_ARRAY);

        for (int i = 0; i < resultsArray.length(); i++) {
            MovieInfo movieInfo = getMovieInfo(resultsArray.getJSONObject(i));
            movieInfoList.add(movieInfo);
        }

        return movieInfoList;
    }

    @NonNull
    private MovieInfo getMovieInfo(JSONObject movieResult)
            throws JSONException, ParseException
    {
        MovieInfo movieInfo = new MovieInfo();
        String posterPath = movieResult.getString(POSTER_PATH);

        movieInfo.setOriginalTitle(movieResult.getString(ORIGINAL_TITLE));

        if (posterPath.equals(JSON_NULL))
            movieInfo.setMoviePosterImageThumbnail(null);
        else
            movieInfo.setMoviePosterImageThumbnail(IMAGES_BASE_URL + posterPath);

        movieInfo.setSynopsis(movieResult.getString(SYNOPSIS));
        movieInfo.setRating(new BigDecimal(movieResult.getString(RATING)));
        movieInfo.setReleaseDate(DATE_FORMAT.parse(movieResult.getString(RELEASE_DATE)));

        return movieInfo;
    }
}
