package com.yoy.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yuyang on 2017/3/20.
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{

    private Context mContext;
    protected List<T> mDatas;
    private LayoutInflater mInflater;

    private OnItemClickListener mOnItemClickListener;//单击事件
    private OnItemLongClickListener mOnItemLongClickListener;//长按单击事件

    public BaseRecyclerViewAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    protected abstract int getItemLayoutId();

    protected abstract void convert(BaseViewHolder holder, T bean);

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(getItemLayoutId(),parent,false);
        BaseViewHolder holder = new BaseViewHolder(view);
        setListener(parent,holder,viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder,mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        if(mDatas == null){
            return 0;
        }else {
            return mDatas.size();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    protected void setListener(final ViewGroup parent, final BaseViewHolder viewHolder, int viewType) {

//        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnItemClickListener != null) {
//                    int position = viewHolder.getAdapterPosition();
//                    mOnItemClickListener.onItemClick(v , position);
//                }
//            }
//        });

//        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (mOnItemLongClickListener != null) {
//                    int position = viewHolder.getAdapterPosition();
//                    mOnItemLongClickListener.onItemLongClick(v,position);
//                }
//                return false;
//            }
//        });
    }

    public void setDatas(List<T> datas){
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setDatas(List<T> datas, boolean needNotify){
        this.mDatas = datas;
        if (needNotify) notifyDataSetChanged();
    }

    public Context getContext() {
        return mContext;
    }

    public void refresh(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public void refreshMore(List<T> datas){
        if(mDatas == null){
            refresh(datas);
        }else{
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }
}
