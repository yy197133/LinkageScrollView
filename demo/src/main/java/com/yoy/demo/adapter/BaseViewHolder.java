package com.yoy.demo.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yuyang on 2017/3/20.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void setText(@IdRes int id, @StringRes int textId) {
        try {
            TextView tx = getView(id);
            tx.setText(textId);
        } catch (Exception e) {
            throw new RuntimeException("TextView is null");
        }
    }

    public void setText(@IdRes int id, String text) {
        try {
            TextView tx = getView(id);
            tx.setText(text);
        } catch (Exception e) {
            throw new RuntimeException("TextView is null");
        }

    }



    public View getConvertView() {
        return mConvertView;
    }
}
