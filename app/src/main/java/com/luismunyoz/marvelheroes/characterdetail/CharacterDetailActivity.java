package com.luismunyoz.marvelheroes.characterdetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luismunyoz.marvelheroes.R;
import com.luismunyoz.marvelheroes.data.source.CharactersRepository;
import com.luismunyoz.marvelheroes.data.source.remote.CharactersRemoteDataSource;
import com.luismunyoz.marvelheroes.util.ActivityUtils;
import com.luismunyoz.marvelheroes.data.Character;

public class CharacterDetailActivity extends AppCompatActivity {

    public static final String ARG_CHARACTERID = "character_id";
    public static final String ARG_CHARACTER = "character";

    private CharacterDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        CharacterDetailFragment characterDetailFragment = (CharacterDetailFragment) getSupportFragmentManager().findFragmentById(R.id.character_container);
        if(characterDetailFragment == null){
            characterDetailFragment = CharacterDetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), characterDetailFragment, R.id.character_container);
        }

        presenter = new CharacterDetailPresenter(CharactersRepository.getInstance(new CharactersRemoteDataSource()), characterDetailFragment);

        Character character = (Character) getIntent().getSerializableExtra(ARG_CHARACTER);
        Long characterId = getIntent().getLongExtra(ARG_CHARACTERID, -1);

        if(character != null){
            presenter.setCharacter(character);
        } else if(!characterId.equals(-1L)){
            presenter.loadCharacter(characterId);
        }
    }
}
