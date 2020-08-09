package iammert.com.androidarchitecture.data.remote.model;


import java.util.List;

import iammert.com.androidarchitecture.data.local.entity.PopularMovieEntity;

/**
 * Develop by mehmetbalbay on 09-08-2020.
 */

public class PopularMoviesResponse {
    private List<PopularMovieEntity> results;

    public List<PopularMovieEntity> getResults() {
        return results;
    }

    public void setResults(List<PopularMovieEntity> results) {
        this.results = results;
    }
}
