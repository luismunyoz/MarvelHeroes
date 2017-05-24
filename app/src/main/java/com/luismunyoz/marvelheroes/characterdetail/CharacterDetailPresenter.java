package com.luismunyoz.marvelheroes.characterdetail;

import com.luismunyoz.marvelheroes.data.Character;
import com.luismunyoz.marvelheroes.data.Comic;
import com.luismunyoz.marvelheroes.data.source.CharactersDataSource;
import com.luismunyoz.marvelheroes.data.source.CharactersRepository;

import java.util.List;

/**
 * Created by Luis on 23/05/2017.
 */

public class CharacterDetailPresenter implements CharacterDetailContract.Presenter {

    private Long characterId;
    private Character character;

    private CharactersRepository repository;
    private CharacterDetailContract.View view;

    public CharacterDetailPresenter(CharactersRepository repository, CharacterDetailContract.View view) {
        this.repository = repository;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        if(character != null){
            view.showCharacter(character);
            loadCharactersComics();
        } else if (characterId != null){
            view.showLoading(true);
            repository.loadCharacter(characterId, new CharactersDataSource.GetCharacterCallback() {
                @Override
                public void onCharacterLoaded(Character character) {
                    view.showLoading(false);
                    CharacterDetailPresenter.this.character = character;
                    view.showCharacter(character);
                    loadCharactersComics();
                }

                @Override
                public void onCharacterLoadError() {
                    view.showLoading(false);
                    view.showErrorLoadingCharacter();
                }
            });
        }
    }

    @Override
    public void loadCharacter(Long characterId) {
        this.characterId = characterId;
    }

    @Override
    public void setCharacter(Character character) {
        this.character = character;
        this.characterId = character.getId();
    }

    @Override
    public void loadCharactersComics() {
        view.showComicLoading(true);
        repository.loadCharacterComics(characterId, new CharactersDataSource.GetCharacterComicsCallback() {
            @Override
            public void onCharacterComicsLoaded(List<Comic> comics) {
                view.showComicLoading(false);
                if(comics == null || comics.isEmpty()){
                    view.showComicEmptyList();
                } else {
                    view.showComics(comics);
                }
            }

            @Override
            public void onCharacterComicsLoadError() {
                view.showComicLoading(false);
            }
        });
    }
}
