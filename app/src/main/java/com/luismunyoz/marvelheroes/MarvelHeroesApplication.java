package com.luismunyoz.marvelheroes;

import android.app.Application;

import com.luismunyoz.marvelheroes.data.source.remote.connection.MarvelService;
import com.luismunyoz.marvelheroes.data.source.remote.connection.WebServices;

/**
 * Created by luis on 02/04/17.
 */

public class MarvelHeroesApplication extends Application {

    private static MarvelHeroesApplication instance;
    private static MarvelService service;

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null) {
            instance = this;
        }

        if (service == null) {
            WebServices webServices = new WebServices(this);
            service = webServices.getService();
        }
    }

    public static MarvelHeroesApplication getInstance() {
        return instance;
    }

    public static MarvelService getService() {
        return service;
    }
}
