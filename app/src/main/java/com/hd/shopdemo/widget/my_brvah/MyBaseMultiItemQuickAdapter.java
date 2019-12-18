package com.hd.shopdemo.widget.my_brvah;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public abstract class MyBaseMultiItemQuickAdapter<T extends MyMultiItemEntity, K extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T, K> {
    private final int typeCount;
    /**
     * indexs[*][]    数据类型ItemEntityType
     * indexs[][0]    起始位置index
     * indexs[][1]    结束位置index，实际结束位置+1
     * 示例，若有数据从3开始存储，共四条，则indexs[*][0]=3,indexs[*][1]=3+4=7
     * 但实际数据index是3、4、5、6
     */
    private int[][] indexs;

    public MyBaseMultiItemQuickAdapter(List<T> data, int typeCount) {
        super(data);
        this.typeCount = typeCount;
        this.indexs = new int[typeCount][2];
        for (int x = 0; x < typeCount; x++) {
            this.indexs[x][0] = -1;
            this.indexs[x][1] = -1;
        }
    }

    public void setCenterDataItem(int type, List<T> newItem) {
        synchronized (this) {
            if (indexs[type][0] == -1 || indexs[type][1] == -1) {
                int startIndex = -1, endIndex = 0;
                if (type != 0)
                    startIndex = indexs[type - 1][1];
                if (startIndex == -1)
                    startIndex = 0;

                addData(startIndex, newItem);
                indexs[type][0] = startIndex;
                indexs[type][1] = startIndex + newItem.size();

                for (int i = type + 1; i < typeCount; i++) {
                    if (indexs[i][0] == -1)
                        indexs[i][0] = 0;
                    if (indexs[i][1] == -1)
                        indexs[i][1] = 0;
                    indexs[i][0] = indexs[i][0] + newItem.size();
                    indexs[i][1] = indexs[i][1] + newItem.size();
                }
            } else {
                int startIndex = -1, endIndex = 0;
                startIndex = indexs[type][0];
                endIndex = indexs[type][1];
                List<T> itemList = getData();

                int count = (endIndex - startIndex) - newItem.size();

                int x = startIndex;
                int y = 0;
                while (x < endIndex && y < newItem.size()) {
                    itemList.get(x).setNewItem(newItem.get(y));
                    notifyItemChanged(x);
                    x++;
                    y++;
                }

                if (y < newItem.size()) {
                    //新条目仍有剩余
                    while (y < newItem.size()) {
                        itemList.add(x, newItem.get(y));
                        notifyItemInserted(x);
                        x++;
                        y++;
                    }
                } else if (x < endIndex) {
                    while (x < endIndex) {
                        endIndex--;
                        itemList.remove(endIndex);
                        notifyItemRemoved(endIndex);
                    }
                }
                endIndex = startIndex;
                for (int i = type + 1; i < typeCount; i++) {
                    indexs[i][0] = indexs[i][0] - count;
                    indexs[i][1] = indexs[i][1] - count;
                }

                indexs[type][0] = startIndex;
                indexs[type][1] = startIndex + newItem.size();
            }
        }
    }

    public void addBottomDataIndex(int type, List<T> bottomDataItemList) {
        int pos = getData().size();
        int count = bottomDataItemList.size();
        indexs[type][1] += count;
        getData().addAll(bottomDataItemList);
        notifyItemRangeInserted(pos, count);
    }
}
