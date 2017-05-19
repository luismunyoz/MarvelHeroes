package com.luismunyoz.marvelheroes;

/**
 * Created by luis on 19/05/17.
 */

public interface BaseContract {

    public interface BasePresenter {

        void start();

    }

    public interface BaseView<T extends BasePresenter> {

        void setPresenter(T presenter);

    }

}
