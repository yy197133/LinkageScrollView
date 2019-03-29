package com.yoy.linkagescrollview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by yuyang on 2017/11/9.
 */

public class ListenedHorizontalScrollView extends HorizontalScrollView {



    private onScrollListener onScrollListener;

    public interface onScrollListener{
        void onScroll(int l, int t, int oldl, int oldt);
    }

    public ListenedHorizontalScrollView(Context context) {
        this(context,null);
    }

    public ListenedHorizontalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ListenedHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null){
            onScrollListener.onScroll(l, t, oldl, oldt);
        }
    }

    public void setOnScrollListener(ListenedHorizontalScrollView.onScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }
}
