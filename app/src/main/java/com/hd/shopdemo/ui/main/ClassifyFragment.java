package com.hd.shopdemo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hd.shopdemo.MainActivity;
import com.hd.shopdemo.R;
import com.hd.shopdemo.base.BaseFragment;
import com.hd.shopdemo.bean.BaseBean;
import com.hd.shopdemo.bean.CustomData;
import com.hd.shopdemo.ui.classify.ClassifyLeftAdapter;
import com.hd.shopdemo.ui.classify.ClassifyLeftItem;
import com.hd.shopdemo.ui.classify.ClassifyRightAdapter;
import com.hd.shopdemo.ui.classify.ClassifyRightItem;
import com.hd.shopdemo.ui.classify.bean.ClassifyLeftBean;
import com.hd.shopdemo.ui.classify.bean.ClassifyRightBean;
import com.hd.shopdemo.utils.LogUtil;
import com.hd.shopdemo.utils.retrofit_utils.RetrofitUtil;
import com.hd.shopdemo.utils.status_bar_utils.StatusBarUtil;
import com.hd.shopdemo.widget.center_linear_layout_manager.CenterLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private int statusBarBgColor;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatusBarUtil.setPaddingSmart(mActivity, ll_nav);
        statusBarBgColor = StatusBarUtil.getStatusBarBg(mActivity);
        initViews();
        initDatas();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.getmPrevious() == 1) {
            StatusBarUtil.immersive(mActivity, statusBarBgColor, 0);
            initDatas();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            StatusBarUtil.immersive(mActivity, statusBarBgColor, 0);
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
                    rcv_classifyLeft.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadClassifyRightList();
                        }
                    }, 200);
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
        loadClassifLeftList();
    }

    private void loadClassifLeftList() {
        RetrofitUtil.getInstance().getClassifyLeftItemBean(new Callback<BaseBean<ClassifyLeftBean>>() {
            @Override
            public void onResponse(@Nullable Call<BaseBean<ClassifyLeftBean>> call, @Nullable Response<BaseBean<ClassifyLeftBean>> response) {
                LogUtil.i("分类   req：" + new Gson().toJson(response));
                if (response != null) {
                    BaseBean<ClassifyLeftBean> bean = response.body();

                    if (bean != null) {
                        LogUtil.i("基类JSON：" + bean.getJSON_Str());
                        if (bean.getCode() == 200) {
                            setLeftAdapterData(bean.getData());
                        } else {
                            Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@Nullable Call<BaseBean<ClassifyLeftBean>> call, @Nullable Throwable t) {
                LogUtil.e("加载首页内容出现问题：" + t.toString());
            }
        });
    }

    private void setLeftAdapterData(ClassifyLeftBean data) {
        classifyLeftItemList.clear();
        classifyLeftAdapter.notifyDataSetChanged();
        List<ClassifyLeftBean.ClassifyDataListBean> leftDataList = data.getClassifyDataList();
        for (int i = 0; i < leftDataList.size(); i++) {
            if (i == 0)
                classifyLeftItemList.add(new ClassifyLeftItem(i, leftDataList.get(i).getS_title(), true));
            else
                classifyLeftItemList.add(new ClassifyLeftItem(i, leftDataList.get(i).getS_title(), false));
        }
        classifyLeftAdapter.notifyItemRangeInserted(0, leftDataList.size() - 1);
        left_selectPos = 0;
        loadClassifyRightList();
    }

    private void loadClassifyRightList() {
        RetrofitUtil.getInstance().getClassifyRightItemBean(new Callback<BaseBean<ClassifyRightBean>>() {
            @Override
            public void onResponse(@Nullable Call<BaseBean<ClassifyRightBean>> call, @Nullable Response<BaseBean<ClassifyRightBean>> response) {
                LogUtil.i("分类   req：" + new Gson().toJson(response));
                if (response != null) {
                    BaseBean<ClassifyRightBean> bean = response.body();

                    if (bean != null) {
                        LogUtil.i("基类JSON：" + bean.getJSON_Str());
                        if (bean.getCode() == 200) {
                            setRightAdapterData(bean.getData());
                        } else {
                            Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@Nullable Call<BaseBean<ClassifyRightBean>> call, @Nullable Throwable t) {
                LogUtil.e("加载首页内容出现问题：" + t.toString());
            }
        });
    }

    private void setRightAdapterData(ClassifyRightBean data) {
        classifyRightItemList.clear();
        classifyLeftAdapter.notifyDataSetChanged();

        List<ClassifyRightBean.BannerDataListBean> bannerDataList = data.getBannerDataList();

        if (bannerDataList.size() == 1) {
            ClassifyRightBean.BannerDataListBean bannerData = bannerDataList.get(0);
            classifyRightAdapter.addData(new ClassifyRightItem(ClassifyRightItem.CLASSIFY_RIGHT_AD, 6, bannerData.getS_id(), bannerData.getG_id(), bannerData.getS_img()));
        } else {
            List<CustomData> bannerList = new ArrayList<>();
            for (int i = 0; i < bannerDataList.size(); i++) {
                ClassifyRightBean.BannerDataListBean bannerData = bannerDataList.get(i);
                bannerList.add(new CustomData(bannerData.getS_img(), "", "#ffffff", false));
            }
            classifyRightAdapter.addData(new ClassifyRightItem(ClassifyRightItem.CLASSIFY_RIGHT_BANNER, 6, bannerList));
        }

        List<ClassifyRightBean.SubClassifys> subClassifysList = data.getItemDataList();
        for (int i = 0; i < subClassifysList.size(); i++) {
            ClassifyRightBean.SubClassifys subClassifys = subClassifysList.get(i);
            classifyRightAdapter.addData(new ClassifyRightItem(ClassifyRightItem.CLASSIFY_RIGHT_TITLE, 6, 0, subClassifys.getClass_title()));
            List<ClassifyRightBean.SubClassifys.SubClassifyItem> itemList = subClassifys.getItemList();
            int addItemCount = 3 - (itemList.size() % 3);
            for (int x = 0; addItemCount != 3 && x < addItemCount; x++)
                itemList.add(new ClassifyRightBean.SubClassifys.SubClassifyItem());

            for (int x = 0; x < itemList.size(); x++) {
                ClassifyRightBean.SubClassifys.SubClassifyItem item = itemList.get(x);
                classifyRightAdapter.addData(new ClassifyRightItem(ClassifyRightItem.CLASSIFY_RIGHT_ITEM, 2, x, item.getG_title(), item.getG_img(), x, itemList.size()));
            }
        }
    }

    @OnClick(R.id.et_search_key_1)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_search_key_1:
                break;
        }
    }

}
