package com.luismunyoz.marvelheroes.characters;

import android.support.v4.app.Fragment;

import com.luismunyoz.marvelheroes.data.Character;

import java.util.List;

/**
 * Created by luis on 22/05/17.
 */

public class CharactersFragment extends Fragment implements CharactersContract.View {

    private CharactersContract.Presenter presenter;

    public CharactersFragment() {
    }

    public static CharactersFragment newInstance(){
        CharactersFragment fragment = new CharactersFragment();
        return fragment;
    }

    @Override
    public void setPresenter(CharactersContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading(boolean active) {

    }

    @Override
    public void showCharacters(List<Character> characters) {

    }

    @Override
    public void showEmptyList() {

    }
}
