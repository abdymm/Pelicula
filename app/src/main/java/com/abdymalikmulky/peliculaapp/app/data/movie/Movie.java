package com.abdymalikmulky.peliculaapp.app.data.movie;

import com.abdymalikmulky.peliculaapp.app.data.DatabaseConfig;
import com.abdymalikmulky.peliculaapp.util.EndpointUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;


@Table(database = DatabaseConfig.class)
@Parcel
public class Movie extends BaseModel
{
    @SerializedName("id")
    @Expose
    @Column
    @PrimaryKey(autoincrement = false)
    String id;

    @SerializedName("title")
    @Expose
    @Column
    String title;

    @SerializedName("overview")
    @Expose
    @Column
    String overview;

    @SerializedName("poster_path")
    @Expose
    @Column
    String posterPath;

    @SerializedName("backdrop_path")
    @Expose
    @Column
    String backdropPath;

    @SerializedName("release_date")
    @Expose
    @Column
    String releaseDate;

    @SerializedName("vote_count")
    @Expose
    @Column
    int voteCount;

    @SerializedName("vote_average")
    @Expose
    @Column
    double voteAverage;

    @SerializedName("popularity")
    @Expose
    @Column
    double popularity;


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getOverview()
    {
        return overview;
    }

    public void setOverview(String overview)
    {
        this.overview = overview;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public String getPosterPath()
    {
        return EndpointUtil.POSTER_PATH + posterPath;
    }

    public void setPosterPath(String posterPath)
    {
        this.posterPath = posterPath;
    }

    public String getBackdropPath()
    {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath)
    {
        this.backdropPath = backdropPath;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public double getVoteAverage()
    {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage)
    {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", voteCount=" + voteCount +
                ", voteAverage=" + voteAverage +
                ", popularity=" + popularity +
                '}';
    }
}
