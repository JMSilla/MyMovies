package com.kaelia.mymovies.moviesapi;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MovieQueryService {
    private static final String SORT_BY_POPULARITY_DESC = "popular"; //"top_rated";

    private String apiKey;

    public MovieQueryService(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<MovieInfo> getMostPopularMovies() {
        List<MovieInfo> movieInfoList = new ArrayList<>();
        ApiRequest request = null;

        try {
            request = new ApiRequest(apiKey, SORT_BY_POPULARITY_DESC);
            request.doRequest();
            movieInfoList = new JsonParser(request.getResponse()).getMovieInfoList();
        } catch (ParseException | JSONException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (request != null)
                request.disconnect();
        }

        return movieInfoList;
    }
}
