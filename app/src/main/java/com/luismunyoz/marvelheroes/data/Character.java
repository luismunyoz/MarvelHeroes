package com.luismunyoz.marvelheroes.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luism on 02/04/2017.
 */

public class Character {

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbnail")
    private Picture thumbnail;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Picture getThumbnail() {
        return thumbnail;
    }

    public String getPictureUrl(){
        if(thumbnail != null){
            return String.format("%1$s.%2$s", getThumbnail().getPath(), getThumbnail().getExtension());
        }
        return "";
    }
}
