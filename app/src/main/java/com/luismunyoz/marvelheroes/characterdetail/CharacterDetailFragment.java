package com.luismunyoz.marvelheroes.characterdetail;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luismunyoz.marvelheroes.R;
import com.luismunyoz.marvelheroes.data.Character;
import com.luismunyoz.marvelheroes.databinding.FragmentCharacterDetailBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Luis on 23/05/2017.
 */

public class CharacterDetailFragment extends Fragment implements CharacterDetailContract.View {

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    private Unbinder unbinder;
    private CharacterDetailContract.Presenter presenter;
    private FragmentCharacterDetailBinding binding;

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

        return view;
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
    public void showCharacter(Character character) {
        binding.setCharacter(character);
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
}
