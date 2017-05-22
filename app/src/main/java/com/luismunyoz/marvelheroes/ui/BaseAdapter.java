package com.luismunyoz.marvelheroes.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by Luis on 22/05/2017.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<MyViewHolder> {

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new MyViewHolder(binding);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Object item = getItemForPosition(position);
        holder.bind(item, getListener());
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    protected abstract Object getItemForPosition(int position);

    protected abstract Object getListener();

    protected abstract int getLayoutIdForPosition(int position);
}
