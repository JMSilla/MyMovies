package com.kaelia.mymovies.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivityFragment extends Fragment {

    public DetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View viewRoot = inflater.inflate(R.layout.fragment_details, container, false);
        Intent activityIntent = getActivity().getIntent();

        TextView title = (TextView) viewRoot.findViewById(R.id.movie_title);
        ImageView moviePosterThumbnail = (ImageView) viewRoot.findViewById(R.id.movie_poster_thumbnail);
        TextView releaseDate = (TextView) viewRoot.findViewById(R.id.release_date);
        TextView userRating = (TextView) viewRoot.findViewById(R.id.user_rating);
        TextView synopsis = (TextView) viewRoot.findViewById(R.id.synopsis);

        title.setText(activityIntent.getStringExtra("ORIGINAL_TITLE"));
        Picasso.with(getActivity()).load(activityIntent.getStringExtra("MOVIE_POSTER_THUMBNAIL"))
            .into(moviePosterThumbnail);
        releaseDate.setText(activityIntent.getStringExtra("RELEASE_DATE"));
        userRating.setText(Float.toString(activityIntent.getFloatExtra("USER_RATING", 0.0F)));
        synopsis.setText(activityIntent.getStringExtra("SYNOPSIS"));

        return viewRoot;
    }
}
