package com.luismunyoz.marvelheroes.data.source;

/**
 * Created by luis on 19/05/17.
 */

public class CharactersRepository {

    private static CharactersRepository INSTANCE;

    private CharactersDataSource remoteDataSource;

    private CharactersRepository(CharactersDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static CharactersRepository getInstance(CharactersDataSource remoteDataSource){
        if(INSTANCE == null){
            INSTANCE = new CharactersRepository(remoteDataSource);
        }
        return INSTANCE;
    }

    private void loadCharacters(CharactersDataSource.GetCharactersCallback callback){
        remoteDataSource.getCharacters(callback);
    }
}
