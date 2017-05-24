package com.luismunyoz.marvelheroes.data.source;

import android.util.SparseArray;

import com.luismunyoz.marvelheroes.BuildConfig;
import com.luismunyoz.marvelheroes.data.Character;
import com.luismunyoz.marvelheroes.data.Comic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by luis on 19/05/17.
 */

public class CharactersRepository {

    public static final int PAGE_SIZE = BuildConfig.DEFAULT_PAGE_SIZE;

    private static CharactersRepository INSTANCE;
    private List<Character> characterList;
    private HashMap<Long, List<Comic>> comicsByCharacterId;

    private Integer currentOffset;

    private CharactersDataSource remoteDataSource;

    private CharactersRepository(CharactersDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
        currentOffset = 0;
        characterList = new ArrayList<>();
        comicsByCharacterId = new HashMap<>();
    }

    public static CharactersRepository getInstance(CharactersDataSource remoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new CharactersRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    public void loadCharacters(final CharactersDataSource.GetCharactersCallback callback){
        remoteDataSource.getCharacters(PAGE_SIZE, currentOffset, new CharactersDataSource.GetCharactersCallback() {
            @Override
            public void onCharactersLoaded(List<Character> characters) {
                for(Character character : characters){
                    if(!characterList.contains(character)){
                        characterList.add(character);
                    }
                }
                if(callback != null){
                    currentOffset += PAGE_SIZE;
                    callback.onCharactersLoaded(characterList);
                }
            }

            @Override
            public void onCharactersLoadError() {
                if(callback != null) {
                    callback.onCharactersLoadError();
                }
            }
        });
    }

    public void loadCharacter(Long characterId, final CharactersDataSource.GetCharacterCallback callback){
        if(callback == null){
            return;
        }
        if(characterList != null){
            for(Character character : characterList){
                if(character.getId().equals(characterId)){
                    callback.onCharacterLoaded(character);
                    return;
                }
            }
        }
        remoteDataSource.getCharacter(characterId, new CharactersDataSource.GetCharacterCallback() {
            @Override
            public void onCharacterLoaded(Character character) {
                if(characterList != null && !characterList.contains(character)){
                    characterList.add(character);
                }
                if(callback != null){
                    callback.onCharacterLoaded(character);
                }
            }

            @Override
            public void onCharacterLoadError() {
                if(callback != null){
                    callback.onCharacterLoadError();
                }
            }
        });
    }

    public void loadCharacterComics(final Long characterId, final CharactersDataSource.GetCharacterComicsCallback callback){
        if(callback == null){
            return;
        }
        if(comicsByCharacterId != null && comicsByCharacterId.containsKey(characterId)){
            callback.onCharacterComicsLoaded(comicsByCharacterId.get(characterId));
            return;
        }
        remoteDataSource.getCharacterComics(characterId, 100, 0, new CharactersDataSource.GetCharacterComicsCallback() {
            @Override
            public void onCharacterComicsLoaded(List<Comic> comics) {
                comicsByCharacterId.put(characterId, comics);
                if(callback != null){
                    callback.onCharacterComicsLoaded(comics);
                }
            }

            @Override
            public void onCharacterComicsLoadError() {
                if(callback != null){
                    callback.onCharacterComicsLoadError();
                }
            }
        });
    }
}
