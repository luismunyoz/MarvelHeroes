package com.luismunyoz.marvelheroes.data.source.remote.interactors;

import java.util.List;
import com.luismunyoz.marvelheroes.data.Character;

/**
 * Created by Luism on 02/04/2017.
 */

public interface CharactersInteractor {

    interface CharactersInteractorCallback {
        void onCharactersDownloaded(List<Character> characters);
        void onCharactersDownloadError(Integer status, String error);
    }

    public void execute(Integer limit, Integer offset, CharactersInteractorCallback callback);
}
