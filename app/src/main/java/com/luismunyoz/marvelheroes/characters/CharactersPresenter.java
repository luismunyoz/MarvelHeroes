package com.luismunyoz.marvelheroes.characters;

import com.luismunyoz.marvelheroes.data.Character;
import com.luismunyoz.marvelheroes.data.source.CharactersDataSource;

import java.util.List;

/**
 * Created by luis on 22/05/17.
 */

public class CharactersPresenter implements CharactersContract.Presenter, CharactersDataSource.GetCharactersCallback {

    private CharactersDataSource dataSource;

    public CharactersPresenter(CharactersDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void start() {
        dataSource.getCharacters(this);
    }

    @Override
    public void loadCharacters() {

    }

    @Override
    public void openCharacterDetails(Character character) {

    }

    @Override
    public void onCharactersLoaded(List<Character> characters) {

    }

    @Override
    public void onCharactersLoadError() {

    }
}
