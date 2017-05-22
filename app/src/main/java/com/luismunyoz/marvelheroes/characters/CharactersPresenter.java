package com.luismunyoz.marvelheroes.characters;

import com.luismunyoz.marvelheroes.data.Character;
import com.luismunyoz.marvelheroes.data.source.CharactersDataSource;

import java.util.List;

/**
 * Created by luis on 22/05/17.
 */

public class CharactersPresenter implements CharactersContract.Presenter, CharactersDataSource.GetCharactersCallback {

    private CharactersDataSource dataSource;
    private CharactersContract.View view;

    public CharactersPresenter(CharactersDataSource dataSource, CharactersContract.View view) {
        this.dataSource = dataSource;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        loadCharacters();
    }

    @Override
    public void loadCharacters() {
        view.showLoading(true);
        dataSource.getCharacters(this);
    }

    @Override
    public void openCharacterDetails(Character character) {

    }

    @Override
    public void onCharactersLoaded(List<Character> characters) {
        view.showLoading(false);
        if(characters.size() > 0) {
            view.showCharacters(characters);
        } else {
            view.showEmptyList();
        }
    }

    @Override
    public void onCharactersLoadError() {
        view.showLoading(false);
        view.showErrorLoading();
    }
}
