package com.luismunyoz.marvelheroes.characterdetail;


import com.luismunyoz.marvelheroes.data.Comic;
import com.luismunyoz.marvelheroes.ui.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luism on 02/04/2017.
 */

public class CharacterComicsAdapter extends BaseAdapter {

    private List<Comic> items;
    private final int layout;
    private Listener listener;

    public CharacterComicsAdapter(int layout, Listener listener) {
        this.layout = layout;
        this.listener = listener;
        this.items = new ArrayList<>();
    }

    @Override
    protected Object getItemForPosition(int position) {
        if (items.size() > position) {
            return items.get(position);
        }
        return null;
    }

    @Override
    protected Object getListener() {
        return listener;
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return layout;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setComics(List<Comic> comics){
        this.items = comics;
        notifyDataSetChanged();
    }

    public interface Listener {
        void onClickComic(Comic comic);
    }
}
