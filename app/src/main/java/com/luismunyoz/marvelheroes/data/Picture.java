package com.luismunyoz.marvelheroes.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Luism on 02/04/2017.
 */

public class Picture implements Serializable {

    @SerializedName("path")
    private String path;

    @SerializedName("extension")
    private String extension;

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }
}
