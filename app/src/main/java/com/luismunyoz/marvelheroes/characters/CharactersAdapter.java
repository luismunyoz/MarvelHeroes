package com.luismunyoz.marvelheroes.characters;

import com.luismunyoz.marvelheroes.R;
import com.luismunyoz.marvelheroes.data.Character;
import com.luismunyoz.marvelheroes.ui.BaseAdapter;

import java.util.List;

/**
 * Created by Luis on 22/05/2017.
 */

public class CharactersAdapter extends BaseAdapter {

    private List<Character> items;
    private final int layout;
    private Listener listener;

    public CharactersAdapter(List<Character> items, int layout, Listener listener) {
        this.items = items;
        this.layout = layout;
        this.listener = listener;
    }

    public void setItems(List<Character> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    protected Object getItemForPosition(int position) {
        return items != null && items.size() > position ? items.get(position): null;
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

    public interface Listener {
        void onClickCharacter(Character character);
    }
}
