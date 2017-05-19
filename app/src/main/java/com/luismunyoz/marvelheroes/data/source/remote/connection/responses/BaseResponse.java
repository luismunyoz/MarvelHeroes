package com.luismunyoz.marvelheroes.data.source.remote.connection.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luism on 02/04/2017.
 */

public class BaseResponse<T> {

    @SerializedName("data")
    private ResultsResponse<T> data;

    public ResultsResponse<T> getData() {
        return data;
    }
}
