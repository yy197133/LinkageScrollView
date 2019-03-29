package com.yoy.demo.adapter;

import android.content.Context;

import com.yoy.demo.Bean.DataBean;
import com.yoy.demo.R;

/**
 * Created by yuyang on 2017/11/9.
 */

public class FundsContentAdapter extends BaseRecyclerViewAdapter<DataBean> {

    /**
     * @param context
     */
    public FundsContentAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_funds_content;
    }

    @Override
    protected void convert(BaseViewHolder holder, DataBean bean) {
        holder.setText(R.id.item_funds_content_net_max_ord,bean.str2);
        holder.setText(R.id.item_funds_content_lspri,bean.str3);
        holder.setText(R.id.item_funds_content_rate,bean.str4);
        holder.setText(R.id.item_funds_content_net_main_in,bean.str5);
        holder.setText(R.id.item_funds_content_main_out,bean.str6);

    }



}
