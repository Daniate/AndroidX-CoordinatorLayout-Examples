package com.daniate.androidx_coordinatorlayout_examples;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ItemsAdapter extends BaseQuickAdapter<Item, BaseViewHolder> {
    public ItemsAdapter(@Nullable List<Item> data) {
        super(R.layout.lyt_item, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Item item) {
        helper.setText(R.id.tv_text, item.getText());
    }
}
