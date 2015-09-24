package com.kaelia.mymovies.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {

    public MoviesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_main, container, false);

        List<String> moviesList = new ArrayList<>();
        moviesList.add("Movie 1");
        moviesList.add("Movie 2");
        moviesList.add("Movie 3");
        moviesList.add("Movie 4");
        moviesList.add("Movie 5");

        ArrayAdapter<String> moviesAdapter = new ArrayAdapter<>(
                getActivity(), R.layout.grid_item_movie, R.id.grid_item_movie_textview, moviesList);

        GridView gridMovies = (GridView) viewRoot.findViewById(R.id.gridview_movies);
        gridMovies.setAdapter(moviesAdapter);

        return viewRoot;
    }
}
