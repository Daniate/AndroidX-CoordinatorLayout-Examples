package com.daniate.androidx_coordinatorlayout_examples;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SwipeRecyclerView recycler_view;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));

        List<Item> itemList = new ArrayList<>();
        {
            Item item = new Item();
            item.setText("滚动悬停");
            item.setActivityClass(ScrollPinTopActivity.class);
            itemList.add(item);
        }
        {
            Item item = new Item();
            item.setText("滚动悬停 & 下拉时，顶部图片缩放");
            item.setActivityClass(ScrollScaleActivity.class);
            itemList.add(item);
        }

        itemsAdapter = new ItemsAdapter(itemList);
        recycler_view.setAdapter(itemsAdapter);

        final Context pkgCtx = this;
        itemsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Item item = itemsAdapter.getItem(position);
                if (null != item.getActivityClass()) {
                    Intent intent = new Intent(pkgCtx, item.getActivityClass());
                    startActivity(intent);
                }
            }
        });
    }

}
