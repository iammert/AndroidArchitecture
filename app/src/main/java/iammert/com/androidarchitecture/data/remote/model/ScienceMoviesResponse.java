package iammert.com.androidarchitecture.data.remote.model;


import java.util.List;

import iammert.com.androidarchitecture.data.local.entity.ScienceMovieEntity;

/**
 * Develop by mehmetbalbay on 09-08-2020.
 */

public class ScienceMoviesResponse {
    private List<ScienceMovieEntity> results;

    public List<ScienceMovieEntity> getResults() {
        return results;
    }

    public void setResults(List<ScienceMovieEntity> results) {
        this.results = results;
    }
}
