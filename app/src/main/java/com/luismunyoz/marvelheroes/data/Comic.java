package com.luismunyoz.marvelheroes.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * Created by Luism on 02/04/2017.
 */

public class Comic implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbnail")
    private Picture thumbnail;

    @SerializedName("images")
    private List<Picture> images;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Picture getThumbnail() {
        return thumbnail;
    }

    public List<Picture> getImages() {
        return images;
    }

    public String getRandomImageURL(){
        if(images != null && images.size() > 0){
            Random random = new Random();
            int index = random.nextInt(images.size());
            Picture image = images.get(index);
            return image.getPath() + "." + image.getExtension();
        }
        return "";
    }
}
