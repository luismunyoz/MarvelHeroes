package com.luismunyoz.marvelheroes.characterdetail;

import com.luismunyoz.marvelheroes.BaseContract;
import com.luismunyoz.marvelheroes.data.Character;
import com.luismunyoz.marvelheroes.data.Comic;

import java.util.List;

/**
 * Created by Luis on 23/05/2017.
 */

public interface CharacterDetailContract {

    interface View extends BaseContract.BaseView<Presenter> {

        void showLoading(boolean active);

        void showCharacter(Character character);

        void showErrorLoadingCharacter();

        void showComics(List<Comic> comics);

        void showComicLoading(boolean loading);

        void showComicEmptyList();
    }

    interface Presenter extends BaseContract.BasePresenter {

        void loadCharacter(Long characterId);

        void setCharacter(Character character);

        void loadCharactersComics();

    }

}
