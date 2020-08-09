package iammert.com.androidarchitecture.data.remote.model;


import java.util.List;

import iammert.com.androidarchitecture.data.local.entity.ComedyMovieEntity;

/**
 * Develop by mehmetbalbay on 09-08-2020.
 */

public class ComedyMoviesResponse {
    private List<ComedyMovieEntity> results;

    public List<ComedyMovieEntity> getResults() {
        return results;
    }

    public void setResults(List<ComedyMovieEntity> results) {
        this.results = results;
    }
}
