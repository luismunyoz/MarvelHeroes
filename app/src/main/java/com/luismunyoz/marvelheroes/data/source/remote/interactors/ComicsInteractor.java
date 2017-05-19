package com.luismunyoz.marvelheroes.data.source.remote.interactors;


import com.luismunyoz.marvelheroes.data.Comic;

import java.util.List;

/**
 * Created by Luism on 02/04/2017.
 */

public interface ComicsInteractor {

    interface ComicsInteractorCallback {
        void onComicsDownloaded(List<Comic> comics);
        void onComicsDownloadError(Integer status, String error);
    }

    public void execute(String characterID, ComicsInteractorCallback callback);
}
