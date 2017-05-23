package com.luismunyoz.marvelheroes.data.source.remote;

import com.luismunyoz.marvelheroes.data.Character;
import com.luismunyoz.marvelheroes.data.source.CharactersDataSource;
import com.luismunyoz.marvelheroes.data.source.remote.interactors.CharactersInteractor;
import com.luismunyoz.marvelheroes.data.source.remote.interactors.CharactersInteractorImpl;

import java.util.List;

/**
 * Created by luis on 19/05/17.
 */

public class CharactersRemoteDataSource implements CharactersDataSource {
    @Override
    public void getCharacters(Integer limit, Integer offset, final GetCharactersCallback callback) {
        (new CharactersInteractorImpl()).execute(limit, offset, new CharactersInteractor.CharactersInteractorCallback() {
            @Override
            public void onCharactersDownloaded(List<Character> characters) {
                if(callback != null){
                    callback.onCharactersLoaded(characters);
                }
            }

            @Override
            public void onCharactersDownloadError(Integer status, String error) {
                if(callback != null){
                    callback.onCharactersLoadError();
                }
            }
        });
    }
}
