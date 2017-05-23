package com.luismunyoz.marvelheroes.characterdetail;

import com.luismunyoz.marvelheroes.BaseContract;
import com.luismunyoz.marvelheroes.data.Character;

/**
 * Created by Luis on 23/05/2017.
 */

public interface CharacterDetailContract {

    interface View extends BaseContract.BaseView<Presenter> {

        void showLoading(boolean active);

        void showCharacter(Character character);

        void showErrorLoadingCharacter();
    }

    interface Presenter extends BaseContract.BasePresenter {

        void loadCharacter(Long characterId);

        void setCharacter(Character character);

    }

}
