package com.yoy.linkagescrollview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yoy.linkagescrollview.listener.OnRecyclerItemClickListener;
import com.yoy.linkagescrollview.widget.ListenedHorizontalScrollView;

import java.util.List;

public class LinkageScrollView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "LinkageScrollView";

    private LayoutInflater mInflater;

    private RecyclerView mNameRv,mContentRv,mTouchedRv, mAnotherRv;
    private ListenedHorizontalScrollView mContentTitleHs, mContentHs;
    private LinearLayout mTitleLl,mContentTitleLl;
    private SmartRefreshLayout mRefreshLayout;

    private int contentTitleViewId = 0;
    private int nameTitleViewId = 0;
    private boolean isRefreshEnable = false;

    private List<String> titles;

    private OnTitleClickListener mOnTitleClickListener;
    private OnItemClickListener mOnItemClickListener;



    public interface OnTitleClickListener{
        void onTitleClick(View v, int position);
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }


    public LinkageScrollView(Context context) {
        this(context,null);
    }

    public LinkageScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LinkageScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        mInflater.inflate(R.layout.linkage_view,this);

        mNameRv = findViewById(R.id.linkage_name_rv);
        mContentRv = findViewById(R.id.linkage_content_rv);
        mContentTitleHs = findViewById(R.id.linkage_content_title_hs);
        mContentHs = findViewById(R.id.linkage_content_hs);
        mTitleLl = findViewById(R.id.linkage_title_ll);
        mContentTitleLl = findViewById(R.id.linkage_content_title_ll);
        mRefreshLayout = findViewById(R.id.linkage_smart_refresh_layout);
        mRefreshLayout.setNestedScrollingEnabled(false);


        if (attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LinkageScrollView,0,0);
            try {
                contentTitleViewId = a.getResourceId(R.styleable.LinkageScrollView_content_title_layout,0);
                nameTitleViewId = a.getResourceId(R.styleable.LinkageScrollView_name_title_layout,0);
                isRefreshEnable = a.getBoolean(R.styleable.LinkageScrollView_refresh_enable,false);
            }finally {
                a.recycle();
            }
        }

        mRefreshLayout.setEnabled(isRefreshEnable);

        setViewsToLinkage();

    }




    @SuppressLint("ClickableViewAccessibility")
    private void setViewsToLinkage(){
        mContentTitleHs.setOnScrollListener((l, t, oldl, oldt) -> mContentHs.scrollTo(l,t));
        mContentHs.setOnScrollListener((l, t, oldl, oldt) -> mContentTitleHs.scrollTo(l,t));

        mNameRv.addOnScrollListener(onScrollListener);
        mContentRv.addOnScrollListener(onScrollListener);

        mNameRv.setOnTouchListener(onTouchListener);
        mContentRv.setOnTouchListener(onTouchListener);

    }

    private void inflateNameTitle(){
        if (nameTitleViewId != 0 && titles.size() > 0){
            TextView textView = (TextView) mInflater.inflate(nameTitleViewId,mTitleLl,false);
            textView.setText(titles.get(0));
            textView.setOnClickListener(this);
            mTitleLl.addView(textView,0);
        }
    }

    private void inflateContentTitle(){
        if (contentTitleViewId != 0 && titles.size() > 0){
            for (int i=1;i<titles.size();++i){
                TextView textView  = (TextView) mInflater.inflate(contentTitleViewId,mContentTitleLl,false);
                textView.setText(titles.get(i));
                textView.setOnClickListener(this);
                mContentTitleLl.addView(textView);
            }
        }
    }


    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (mTouchedRv == recyclerView)
                mAnotherRv.scrollBy(dx,dy);
        }
    };

    private OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getActionMasked()){
                case MotionEvent.ACTION_DOWN:
                    if (mTouchedRv != v){
                        mTouchedRv = (RecyclerView) v;
                        mAnotherRv = mNameRv == mTouchedRv ? mContentRv : mNameRv;
                    }
                    //如果另外一个recycler view 正在滚动
                    if (mAnotherRv.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING)
                        mAnotherRv.stopScroll();

                    break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                    break;
                default:
                    break;
            }
            return false;
        }
    };

    @Override
    public void onClick(View v) {
        if (mOnTitleClickListener != null){
            TextView tv = (TextView) v;
            for (int i=0;i<titles.size();++i){
                if (tv.getText().toString().equals(titles.get(i))){
                    mOnTitleClickListener.onTitleClick(v,i);
                }
            }
        }
    }

    private void setOnItemClickListener(RecyclerView recyclerView){
        recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(recyclerView) {
            @Override
            public void OnItemClicked(View v, int position) {
                mOnItemClickListener.onItemClick(v,position);
            }
        });

    }



    public RecyclerView getNameRecyclerView(){
        return mNameRv;
    }

    public RecyclerView getContentRecyclerView() {
        return mContentRv;
    }

    public SmartRefreshLayout getRefreshLayout() {
        return mRefreshLayout;
    }

    public ListenedHorizontalScrollView getTitleHorizontalScrollView() {
        return mContentTitleHs;
    }

    public ListenedHorizontalScrollView getContentHorizontalScrollView() {
        return mContentHs;
    }

    public void setRefreshEnable(boolean refreshEnable) {
        this.isRefreshEnable = refreshEnable;
        mRefreshLayout.setEnabled(refreshEnable);
    }

    public void setOnTitleClickListener(OnTitleClickListener onTitleClickListener) {
        this.mOnTitleClickListener = onTitleClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        setOnItemClickListener(mNameRv);
        setOnItemClickListener(mContentRv);
    }

    public void setTitles(@NonNull List<String> titles) {
        if (mContentTitleLl.getChildCount() != 0) return;
        this.titles = titles;
        inflateNameTitle();
        inflateContentTitle();
    }
}
