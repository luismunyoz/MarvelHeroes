package com.luismunyoz.marvelheroes.characters;

import com.luismunyoz.marvelheroes.data.Character;
import com.luismunyoz.marvelheroes.data.source.CharactersDataSource;
import com.luismunyoz.marvelheroes.data.source.CharactersRepository;

import java.util.List;

/**
 * Created by luis on 22/05/17.
 */

public class CharactersPresenter implements CharactersContract.Presenter, CharactersDataSource.GetCharactersCallback {

    private CharactersRepository repository;
    private CharactersContract.View view;

    public CharactersPresenter(CharactersRepository charactersRepository, CharactersContract.View view) {
        this.repository = charactersRepository;
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
        repository.loadCharacters(this);
    }

    @Override
    public void onCharacterClicked(Character character) {
        view.openCharacterDetails(character);
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

    @Override
    public void onLoadMore() {
        repository.loadCharacters(this);
    }
}
