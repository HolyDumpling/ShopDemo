package com.hd.shopdemo.widget.my_brvah;


import com.chad.library.adapter.base.entity.MultiItemEntity;

public abstract class MyMultiItemEntity<T extends MyMultiItemEntity> implements MultiItemEntity {
    public abstract void setNewItem(T newItem);
}
