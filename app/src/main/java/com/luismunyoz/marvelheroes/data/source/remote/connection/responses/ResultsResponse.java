package com.luismunyoz.marvelheroes.data.source.remote.connection.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luism on 02/04/2017.
 */

public class ResultsResponse<T> {

    @SerializedName("offset")
    private Integer offset;

    @SerializedName("limit")
    private Integer limit;

    @SerializedName("total")
    private Integer total;

    @SerializedName("count")
    private Integer count;

    @SerializedName("results")
    private List<T> results;

    public List<T> getResults() {
        return results;
    }
}
