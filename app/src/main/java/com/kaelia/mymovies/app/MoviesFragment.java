package com.kaelia.mymovies.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.kaelia.mymovies.moviesapi.MovieInfo;
import com.kaelia.mymovies.moviesapi.MovieQueryService;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {
    private  ArrayAdapter<String> moviesAdapter;

    public MoviesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_main, container, false);

        moviesAdapter = new ArrayAdapter<>(
                getActivity(), R.layout.grid_item_movie, R.id.grid_item_movie_textview,
                new ArrayList<String>());

        GridView gridMovies = (GridView) viewRoot.findViewById(R.id.gridview_movies);
        gridMovies.setAdapter(moviesAdapter);

        new FetchMoviesInfoTask().execute();

        return viewRoot;
    }

    private class FetchMoviesInfoTask extends AsyncTask<Void, Void, List<MovieInfo>> {

        @Override
        protected List<MovieInfo> doInBackground(Void... params) {
            return MovieQueryService.getMostPopularMovies();
        }

        @Override
        protected void onPostExecute(List<MovieInfo> moviesInfoList) {
            moviesAdapter.clear();

            for (MovieInfo movie : moviesInfoList)
                moviesAdapter.add(movie.getOriginalTitle());

            moviesAdapter.notifyDataSetChanged();
        }
    }
}
