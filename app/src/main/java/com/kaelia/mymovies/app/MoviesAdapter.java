package com.kaelia.mymovies.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.kaelia.mymovies.moviesapi.MovieInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends BaseAdapter {
    private Context context;
    private List<MovieInfo> movieInfoList;

    public MoviesAdapter(Context context, List<MovieInfo> movieInfoList) {
        this.context = context;
        this.movieInfoList = movieInfoList;
    }

    public void setMoviesInfoList(List<MovieInfo> movieInfoList) {
        this.movieInfoList = movieInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return movieInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieInfo movie = movieInfoList.get(position);

        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_item_movie, parent, false);
        }

        ImageView moviePoster = (ImageView) convertView.findViewById(R.id.movie_poster);

        Picasso.with(context).load(movie.getMoviePosterImageThumbnail()).into(moviePoster);

        return convertView;
    }
}
