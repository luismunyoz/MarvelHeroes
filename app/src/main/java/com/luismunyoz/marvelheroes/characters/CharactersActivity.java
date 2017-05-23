package com.luismunyoz.marvelheroes.characters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.luismunyoz.marvelheroes.R;
import com.luismunyoz.marvelheroes.data.source.remote.CharactersRemoteDataSource;
import com.luismunyoz.marvelheroes.util.ActivityUtils;

public class CharactersActivity extends AppCompatActivity {

    private CharactersPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);

        CharactersFragment charactersFragment = (CharactersFragment) getSupportFragmentManager().findFragmentById(R.id.characters_container);
        if(charactersFragment == null){
            charactersFragment = CharactersFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), charactersFragment, R.id.characters_container);
        }

        presenter = new CharactersPresenter(new CharactersRemoteDataSource(), charactersFragment);
    }
}
