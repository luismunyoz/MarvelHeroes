package com.luismunyoz.marvelheroes.characterdetail;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.luismunyoz.marvelheroes.R;
import com.luismunyoz.marvelheroes.data.Character;
import com.luismunyoz.marvelheroes.data.Comic;
import com.luismunyoz.marvelheroes.databinding.FragmentCharacterDetailBinding;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Luis on 23/05/2017.
 */

public class CharacterDetailFragment extends Fragment implements CharacterDetailContract.View, CharacterComicsAdapter.Listener {

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.character_comic_list)
    RecyclerView comicList;
    @BindView(R.id.character_comic_loading)
    ProgressBar comicLoading;

    private Unbinder unbinder;
    private CharacterDetailContract.Presenter presenter;
    private FragmentCharacterDetailBinding binding;
    private CharacterComicsAdapter comicsAdapter;

    public CharacterDetailFragment(){

    }

    public static CharacterDetailFragment newInstance() {
        CharacterDetailFragment characterDetailFragment = new CharacterDetailFragment();
        return characterDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_character_detail, container, false);
        View view = binding.getRoot();

        unbinder = ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        comicsAdapter = new CharacterComicsAdapter(R.layout.layout_comic_item, this);
        comicList.setNestedScrollingEnabled(false);
        comicList.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        comicList.setAdapter(comicsAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(CharacterDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading(boolean active) {

    }

    @Override
    public void showErrorLoadingCharacter() {

    }

    @Override
    public void showComics(List<Comic> comics) {
        comicsAdapter.setComics(comics);
    }

    @Override
    public void showComicLoading(boolean loading) {
        if(comicLoading != null) {
            comicLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void showComicEmptyList() {

    }

    @Override
    public void showCharacter(Character character) {
        binding.setCharacter(character);
    }

    @Override
    public void onClickComic(Comic comic) {

    }
}
