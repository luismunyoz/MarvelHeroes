package com.luismunyoz.marvelheroes.data.source.remote.interactors;


import com.luismunyoz.marvelheroes.MarvelHeroesApplication;
import com.luismunyoz.marvelheroes.data.source.remote.connection.responses.BaseResponse;
import com.luismunyoz.marvelheroes.data.Character;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luism on 02/04/2017.
 */

public class CharactersInteractorImpl implements CharactersInteractor {

    @Override
    public void execute(final CharactersInteractorCallback callback) {
        MarvelHeroesApplication.getService().getCharacters().enqueue(new Callback<BaseResponse<Character>>() {
            @Override
            public void onResponse(Call<BaseResponse<Character>> call, Response<BaseResponse<Character>> response) {
                if(callback != null){
                    if(response.isSuccessful()){
                        callback.onCharactersDownloaded(response.body().getData().getResults());
                    } else {
                        try {
                            callback.onCharactersDownloadError(response.code(), response.errorBody().string());
                        } catch (IOException e) {
                            callback.onCharactersDownloadError(response.code(), "Error");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Character>> call, Throwable t) {
                if(callback != null){
                    callback.onCharactersDownloadError(-1, t.getLocalizedMessage());
                }
            }
        });
    }
}
