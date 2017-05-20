package iammert.com.androidarchitecture.data.remote.model;


import java.util.List;

import iammert.com.androidarchitecture.data.local.entity.MovieEntity;

/**
 * Created by mertsimsek on 19/05/2017.
 */

public class MoviesResponse {
    private List<MovieEntity> results;

    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }
}
