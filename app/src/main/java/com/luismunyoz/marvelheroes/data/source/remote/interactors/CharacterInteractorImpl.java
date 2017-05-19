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

public class CharacterInteractorImpl implements CharacterInteractor {

    @Override
    public void execute(String characterID, final CharacterInteractorCallback callback) {
        MarvelHeroesApplication.getService().getCharacter(characterID).enqueue(new Callback<BaseResponse<Character>>() {
            @Override
            public void onResponse(Call<BaseResponse<Character>> call, Response<BaseResponse<Character>> response) {
                if(callback != null){
                    if(response.isSuccessful()){
                        callback.onCharacterDownloaded(response.body().getData().getResults().get(0));
                    } else {
                        try {
                            callback.onCharacterDownloadError(response.code(), response.errorBody().string());
                        } catch (IOException e) {
                            callback.onCharacterDownloadError(response.code(), "Error");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Character>> call, Throwable t) {
                if(callback != null){
                    callback.onCharacterDownloadError(-1, t.getLocalizedMessage());
                }
            }
        });
    }
}
