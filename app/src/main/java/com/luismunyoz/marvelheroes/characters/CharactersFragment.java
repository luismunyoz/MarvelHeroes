package com.luismunyoz.marvelheroes.characters;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luismunyoz.marvelheroes.R;
import com.luismunyoz.marvelheroes.data.Character;
import com.luismunyoz.marvelheroes.ui.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luis on 22/05/17.
 */

public class CharactersFragment extends Fragment implements CharactersContract.View, CharactersAdapter.Listener {

    @BindView(R.id.characters_list)
    RecyclerView list;

    private CharactersContract.Presenter presenter;
    private List<Character> characters;
    private CharactersAdapter adapter;

    public CharactersFragment() {
    }

    public static CharactersFragment newInstance(){
        CharactersFragment fragment = new CharactersFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        characters = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characters, container, false);
        ButterKnife.bind(this, view);

        adapter = new CharactersAdapter(characters, R.layout.layout_character_item, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        list.setLayoutManager(gridLayoutManager);
        list.setAdapter(adapter);
        list.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.onLoadMore();
            }
        });

        return view;
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
        this.characters = characters;
        adapter.setItems(characters);
    }

    @Override
    public void showEmptyList() {

    }

    @Override
    public void showErrorLoading() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onClickCharacter(Character character) {
        presenter.openCharacterDetails(character);
    }
}
