package com.yoy.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.yoy.demo.Bean.DataBean;
import com.yoy.demo.adapter.FundsContentAdapter;
import com.yoy.demo.adapter.FundsNameAdapter;
import com.yoy.linkagescrollview.LinkageScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mNameRv,mContentRv;
    private FundsNameAdapter mNameAdapter;
    private FundsContentAdapter mContentAdapter;

    private LinkageScrollView mLinkageScrollView;

    private List<String> titles = Arrays.asList("名称代码","大单净量","最新","涨跌幅","总手","持仓量","日增量");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinkageScrollView = findViewById(R.id.linkage_scroll_view);
        mLinkageScrollView.setTitles(titles);

        initAdapter();


        List<DataBean> datas = getData();
        mNameAdapter.setDatas(datas);
        mContentAdapter.setDatas(datas);


        mLinkageScrollView.setOnTitleClickListener((v, position) ->
                Toast.makeText(MainActivity.this,"title click "+position,Toast.LENGTH_SHORT).show());

        mLinkageScrollView.setOnItemClickListener((v, position) ->
            Toast.makeText(MainActivity.this,"click "+position,Toast.LENGTH_SHORT).show());


    }

    private List<DataBean> getData(){
        List<DataBean> datas = new ArrayList<>(50);
        for (int i=0;i<50;++i){
            datas.add(new DataBean("大单净量"+i,"最新"+i,"涨跌幅"+i,"总受"+i,"持仓量"+i,"日增长"+i));
        }
        return datas;
    }

    private void initAdapter(){

        mNameRv = mLinkageScrollView.getNameRecyclerView();
        mContentRv = mLinkageScrollView.getContentRecyclerView();

        mNameAdapter = new FundsNameAdapter(this);
        mContentAdapter = new FundsContentAdapter(this);

        mNameRv.setAdapter(mNameAdapter);
        mNameRv.setLayoutManager(new LinearLayoutManager(this));

        mContentRv.setAdapter(mContentAdapter);
        mContentRv.setLayoutManager(new LinearLayoutManager(this));
    }


}
