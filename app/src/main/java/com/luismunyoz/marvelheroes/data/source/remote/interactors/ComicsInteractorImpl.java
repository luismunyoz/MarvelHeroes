package com.luismunyoz.marvelheroes.data.source.remote.interactors;

import com.luismunyoz.marvelheroes.MarvelHeroesApplication;
import com.luismunyoz.marvelheroes.data.Comic;
import com.luismunyoz.marvelheroes.data.source.remote.connection.responses.BaseResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luism on 02/04/2017.
 */

public class ComicsInteractorImpl implements ComicsInteractor {
    @Override
    public void execute(String characterID, final ComicsInteractorCallback callback) {
        MarvelHeroesApplication.getService().getComicsByCharacter(characterID).enqueue(new Callback<BaseResponse<Comic>>() {
            @Override
            public void onResponse(Call<BaseResponse<Comic>> call, Response<BaseResponse<Comic>> response) {
                if(callback != null){
                    if(response.isSuccessful()){
                        callback.onComicsDownloaded(response.body().getData().getResults());
                    } else {
                        try {
                            callback.onComicsDownloadError(response.code(), response.errorBody().string());
                        } catch (IOException e) {
                            callback.onComicsDownloadError(response.code(), "Error");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Comic>> call, Throwable t) {
                if(callback != null){
                    callback.onComicsDownloadError(-1, t.getLocalizedMessage());
                }
            }
        });
    }
}
