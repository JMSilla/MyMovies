package com.kaelia.mymovies.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.kaelia.mymovies.moviesapi.MovieInfo;
import com.kaelia.mymovies.moviesapi.MovieQueryService;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {
    private MoviesAdapter moviesAdapter;

    @Override
    public void onStart() {
        super.onStart();
        updateMovies();
    }

    private void updateMovies() {
        String apiKey = getString(R.string.tmdb_apikey, "");
        String sortOrder = getSortOrderFromPreferences();

        new FetchMoviesInfoTask().execute(apiKey, sortOrder);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_main, container, false);

        moviesAdapter = new MoviesAdapter(getActivity(), new ArrayList<MovieInfo>());

        GridView gridMovies = (GridView) viewRoot.findViewById(R.id.gridview_movies);
        gridMovies.setAdapter(moviesAdapter);
        gridMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieInfo clickedMovie = moviesAdapter.getMovie(position);
                Intent startDetailActivityIntent = new Intent(getActivity(), DetailsActivity.class);

                startDetailActivityIntent.putExtra("ORIGINAL_TITLE", clickedMovie.getOriginalTitle());
                startDetailActivityIntent.putExtra("MOVIE_POSTER_THUMBNAIL",
                        clickedMovie.getMoviePosterImageThumbnail());
                startDetailActivityIntent.putExtra("RELEASE_DATE",
                        clickedMovie.getReleaseDate().toString());
                startDetailActivityIntent.putExtra("USER_RATING",
                        clickedMovie.getRating().floatValue());

                startDetailActivityIntent.putExtra("SYNOPSIS", clickedMovie.getSynopsis());

                startActivity(startDetailActivityIntent);

            }
        });

        return viewRoot;
    }

    private String getSortOrderFromPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(getActivity())
                .getString(getString(R.string.pref_key_sort_type),
                        getString(R.string.most_popular_value));
    }

    private class FetchMoviesInfoTask extends AsyncTask<String, Void, List<MovieInfo>> {

        @Override
        protected List<MovieInfo> doInBackground(String... params) {
            String apiKey = params[0];
            String sortOrder = params[1];
            return new MovieQueryService(apiKey).getMostPopularMovies(sortOrder);
        }

        @Override
        protected void onPostExecute(List<MovieInfo> moviesInfoList) {
            moviesAdapter.setMoviesInfoList(moviesInfoList);
        }
    }
}
