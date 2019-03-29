package com.yoy.linkagescrollview.listener;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnRecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector mGestureDetector;

    public OnRecyclerItemClickListener(final RecyclerView recyclerView) {
        mGestureDetector = new GestureDetector(recyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if (childView != null)
                    OnItemClicked(childView, recyclerView.getChildAdapterPosition(childView));
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if (childView != null){
                    OnItemLongClicked(childView,recyclerView.getChildAdapterPosition(childView));
                }
            }
        });


    }



    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        return mGestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    public void OnItemClicked(View v, int position){}
    public void OnItemLongClicked(View v, int position){}


}
