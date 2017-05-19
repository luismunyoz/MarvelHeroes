package com.luismunyoz.marvelheroes.data.source;

import com.luismunyoz.marvelheroes.data.Character;

import java.util.List;

/**
 * Created by luis on 19/05/17.
 */

public interface CharactersDataSource {

    void getCharacters(GetCharactersCallback callback);

    interface GetCharactersCallback {
        void onCharactersLoaded(List<Character> characters);

        void onCharactersLoadError();
    }
}
