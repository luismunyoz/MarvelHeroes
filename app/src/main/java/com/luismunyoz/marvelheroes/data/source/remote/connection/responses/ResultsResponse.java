package com.luismunyoz.marvelheroes.data.source.remote.connection.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luism on 02/04/2017.
 */

public class ResultsResponse<T> {

    @SerializedName("results")
    private List<T> results;

    public List<T> getResults() {
        return results;
    }
}
