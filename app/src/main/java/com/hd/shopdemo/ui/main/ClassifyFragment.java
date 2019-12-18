package com.hd.shopdemo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hd.shopdemo.MainActivity;
import com.hd.shopdemo.R;
import com.hd.shopdemo.base.BaseFragment;
import com.hd.shopdemo.ui.classify.ClassifyLeftAdapter;
import com.hd.shopdemo.ui.classify.ClassifyLeftItem;
import com.hd.shopdemo.ui.classify.ClassifyRightAdapter;
import com.hd.shopdemo.ui.classify.ClassifyRightItem;
import com.hd.shopdemo.utils.status_bar_utils.StatusBarUtil;
import com.hd.shopdemo.widget.center_linear_layout_manager.CenterLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分类
 */
public class ClassifyFragment extends BaseFragment {
    @BindView(R.id.ll_nav)
    LinearLayout ll_nav;

    @BindView(R.id.rcv_classifyLeft)
    RecyclerView rcv_classifyLeft;

    @BindView(R.id.rcv_classifyRight)
    RecyclerView rcv_classifyRight;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_classify, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatusBarUtil.setPaddingSmart(mActivity, ll_nav);
        initViews();
        initDatas();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.getmPrevious() == 3) {
            initDatas();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            initDatas();
        }
    }

    List<ClassifyLeftItem> classifyLeftItemList;
    ClassifyLeftAdapter classifyLeftAdapter;

    int left_selectPos = -1;
    CenterLayoutManager centerLayoutManager;


    List<ClassifyRightItem> classifyRightItemList;
    ClassifyRightAdapter classifyRightAdapter;

    @Override
    protected void initViews() {
        classifyLeftItemList = new ArrayList<>();
        classifyLeftAdapter = new ClassifyLeftAdapter(mContext, classifyLeftItemList);
        //classifyLeftAdapter.openLoadAnimation();
        //classifyLeftAdapter.setNotDoAnimationCount(3);
        centerLayoutManager = new CenterLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcv_classifyLeft.setLayoutManager(centerLayoutManager);
        rcv_classifyLeft.setAdapter(classifyLeftAdapter);
        classifyLeftAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (left_selectPos >= classifyLeftItemList.size())
                    left_selectPos = -1;
                if (position != left_selectPos) {
                    if (left_selectPos != -1) {
                        classifyLeftItemList.get(left_selectPos).setSelected(false);
                        classifyLeftAdapter.notifyItemChanged(left_selectPos);
                    }
                    left_selectPos = position;
                    classifyLeftItemList.get(left_selectPos).setSelected(true);
                    classifyLeftAdapter.notifyItemChanged(left_selectPos);

                    rcv_classifyLeft.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            centerLayoutManager.smoothScrollToPosition(rcv_classifyLeft, new RecyclerView.State(), position);
                        }
                    }, 100);

                    loadClassifyRightList(classifyLeftItemList.get(position).getC_id());
                }
            }
        });

        classifyRightItemList = new ArrayList<>();
        classifyRightAdapter = new ClassifyRightAdapter(mContext, classifyRightItemList, ClassifyRightItem.CLASSIFY_RIGHT_TYPE_COUNT);
        rcv_classifyRight.setLayoutManager(new GridLayoutManager(mContext, 6));
        rcv_classifyRight.setAdapter(classifyRightAdapter);
    }

    @Override
    protected void initDatas() {
        for (int i = 0; i < 50; i++) {
            if (i == 0)
                classifyLeftItemList.add(new ClassifyLeftItem(i, "分类（" + i + "）", true));
            else
                classifyLeftItemList.add(new ClassifyLeftItem(i, "分类（" + i + "）", false));
            classifyLeftAdapter.notifyItemInserted(classifyLeftItemList.size() - 1);
        }
        loadClassifyRightList(classifyLeftItemList.get(0).getC_id());
    }


    private void loadClassifyRightList(int c_id) {
        classifyRightAdapter.getData().clear();
        classifyLeftAdapter.notifyDataSetChanged();

        classifyRightAdapter.addData(new ClassifyRightItem(ClassifyRightItem.CLASSIFY_RIGHT_AD, 6, c_id, 13, ""));


        classifyRightAdapter.addData(new ClassifyRightItem(ClassifyRightItem.CLASSIFY_RIGHT_TITLE, 6, c_id, "爆款推荐"));
        for (int i = 0; i < 8; i++)
            classifyRightAdapter.addData(new ClassifyRightItem(ClassifyRightItem.CLASSIFY_RIGHT_ITEM, 2, i, "推荐" + i, "", i, 9));

        classifyRightAdapter.addData(new ClassifyRightItem(ClassifyRightItem.CLASSIFY_RIGHT_ITEM, 2, 0, "", "", 8, 9));

        classifyRightAdapter.addData(new ClassifyRightItem(ClassifyRightItem.CLASSIFY_RIGHT_TITLE, 6, c_id, "每日必备"));
        for (int i = 0; i < 5; i++)
            classifyRightAdapter.addData(new ClassifyRightItem(ClassifyRightItem.CLASSIFY_RIGHT_ITEM, 2, i, "必备" + i, "", i, 6));

        classifyRightAdapter.addData(new ClassifyRightItem(ClassifyRightItem.CLASSIFY_RIGHT_ITEM, 2, 0, "", "", 5, 6));

    }

    @OnClick(R.id.et_search_key_1)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_search_key_1:
                break;
        }
    }

}
