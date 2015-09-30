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

public class DetailsFragment extends Fragment {
    public static final String PARAM_ORIGINAL_TITLE = "ORIGINAL_TITLE";
    public static final String PARAM_MOVIE_POSTER_THUMBNAIL = "MOVIE_POSTER_THUMBNAIL";
    public static final String PARAM_RELEASE_YEAR = "RELEASE_YEAR";
    public static final String PARAM_USER_RATING = "USER_RATING";
    public static final String PARAM_SYNOPSIS = "SYNOPSIS";

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View viewRoot = inflater.inflate(R.layout.fragment_details, container, false);
        Intent activityIntent = getActivity().getIntent();

        TextView title = (TextView) viewRoot.findViewById(R.id.movie_title);
        ImageView moviePosterThumbnail = (ImageView) viewRoot.findViewById(R.id.movie_poster_thumbnail);
        TextView releaseDate = (TextView) viewRoot.findViewById(R.id.release_year);
        TextView userRating = (TextView) viewRoot.findViewById(R.id.user_rating);
        TextView synopsis = (TextView) viewRoot.findViewById(R.id.synopsis);

        title.setText(activityIntent.getStringExtra(PARAM_ORIGINAL_TITLE));
        Picasso.with(getActivity()).load(activityIntent.getStringExtra(PARAM_MOVIE_POSTER_THUMBNAIL))
            .into(moviePosterThumbnail);
        releaseDate.setText(Integer.toString((activityIntent.getIntExtra(PARAM_RELEASE_YEAR, 0))));
        userRating.setText(activityIntent.getStringExtra(PARAM_USER_RATING));
        synopsis.setText(activityIntent.getStringExtra(PARAM_SYNOPSIS));

        return viewRoot;
    }
}
