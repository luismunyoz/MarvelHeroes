package com.luismunyoz.marvelheroes.data.source.remote.connection;

import com.luismunyoz.marvelheroes.data.Comic;
import com.luismunyoz.marvelheroes.data.Character;
import com.luismunyoz.marvelheroes.data.source.remote.connection.responses.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Luism on 02/04/2017.
 */

public interface MarvelService {

    @GET("/v1/public/characters/")
    Call<BaseResponse<Character>> getCharacters();

    @GET("/v1/public/characters/{id}")
    Call<BaseResponse<Character>> getCharacter(@Path("id") String id);

    @GET("/v1/public/characters/{id}/comics")
    Call<BaseResponse<Comic>> getComicsByCharacter(@Path("id") String characterId);
}
