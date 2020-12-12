package com.example.moviepass.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity(tableName = "movies")
public class MoviesEntity {

    @PrimaryKey()
    private final int id;

    private final String language;
    private final String title;
    private final String overview;
    private final double popularity;

    @ColumnInfo(name = "poster_path")
    private final String posterPath;

    @ColumnInfo(name = "backdrop_path")
    private final String backdropPath;

    @ColumnInfo(name = "vote_average")
    private final double voteAverage;

    @ColumnInfo(name = "vote_count")
    private final int voteCount;

    @ColumnInfo(name = "release_date")
    private final String releaseDate;

    public MoviesEntity(int id, String language, String title, String overview, double popularity, String posterPath, String backdropPath, double voteAverage, int voteCount, String releaseDate) {
        this.id = id;
        this.language = language;
        this.title = title;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoviesEntity)) return false;
        MoviesEntity that = (MoviesEntity) o;
        return getId() == that.getId() &&
                Double.compare(that.getPopularity(), getPopularity()) == 0 &&
                Double.compare(that.getVoteAverage(), getVoteAverage()) == 0 &&
                getVoteCount() == that.getVoteCount() &&
                Objects.equals(getLanguage(), that.getLanguage()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getOverview(), that.getOverview()) &&
                Objects.equals(getPosterPath(), that.getPosterPath()) &&
                Objects.equals(getBackdropPath(), that.getBackdropPath()) &&
                Objects.equals(getReleaseDate(), that.getReleaseDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLanguage(), getTitle(), getOverview(), getPopularity(), getPosterPath(), getBackdropPath(), getVoteAverage(), getVoteCount(), getReleaseDate());
    }

    @Override
    public String toString() {
        return "MoviesEntity{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
