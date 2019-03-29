package com.yoy.demo.adapter;

import android.content.Context;
import android.widget.TextView;

import com.yoy.demo.Bean.DataBean;
import com.yoy.demo.R;


/**
 * Created by yuyang on 2017/11/9.
 */

public class FundsNameAdapter extends BaseRecyclerViewAdapter<DataBean> {


    /**
     * @param context
     *
     */
    public FundsNameAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_funds_name;
    }

    @Override
    protected void convert(BaseViewHolder holder, DataBean bean) {
        String name = "载入中"+holder.getAdapterPosition();
        String code = "--";
        TextView nameTv = holder.getView(R.id.item_funds_name);

        nameTv.setText(name);

        holder.setText(R.id.item_funds_name_code,code);

    }


}
