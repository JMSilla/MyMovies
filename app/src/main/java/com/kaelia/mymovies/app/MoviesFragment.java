package com.kaelia.mymovies.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.kaelia.mymovies.moviesapi.MovieInfo;
import com.kaelia.mymovies.moviesapi.MovieQueryService;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {
    private MoviesAdapter moviesAdapter;

    public MoviesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_main, container, false);

        moviesAdapter = new MoviesAdapter(getActivity(), new ArrayList<MovieInfo>());

        GridView gridMovies = (GridView) viewRoot.findViewById(R.id.gridview_movies);
        gridMovies.setAdapter(moviesAdapter);

        String apiKey = getString(R.string.tmdb_apikey, "");

        new FetchMoviesInfoTask().execute(apiKey);

        return viewRoot;
    }

    private class FetchMoviesInfoTask extends AsyncTask<String, Void, List<MovieInfo>> {

        @Override
        protected List<MovieInfo> doInBackground(String... params) {
            String apiKey = params[0];
            return new MovieQueryService(apiKey).getMostPopularMovies();
        }

        @Override
        protected void onPostExecute(List<MovieInfo> moviesInfoList) {
            moviesAdapter.setMoviesInfoList(moviesInfoList);
        }
    }
}
