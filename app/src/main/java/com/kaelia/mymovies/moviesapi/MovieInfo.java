package com.kaelia.mymovies.moviesapi;

import java.math.BigDecimal;
import java.util.Date;

public class MovieInfo {
    private String originalTitle;
    private String moviePosterImageThumbnail;
    private String synopsis;
    private BigDecimal rating;
    private Date releaseDate;

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getMoviePosterImageThumbnail() {
        return moviePosterImageThumbnail;
    }

    public void setMoviePosterImageThumbnail(String moviePosterImageThumbnail) {
        this.moviePosterImageThumbnail = moviePosterImageThumbnail;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
