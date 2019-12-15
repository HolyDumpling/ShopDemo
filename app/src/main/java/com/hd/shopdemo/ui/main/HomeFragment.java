package com.hd.shopdemo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hd.shopdemo.MainActivity;
import com.hd.shopdemo.R;
import com.hd.shopdemo.app.AppConfig;
import com.hd.shopdemo.base.BaseFragment;
import com.hd.shopdemo.bean.CustomData;
import com.hd.shopdemo.bean.HomeContentBean;
import com.hd.shopdemo.ui.home.HomeCenterItem;
import com.hd.shopdemo.ui.home.HomeContentAdapter;
import com.hd.shopdemo.utils.LogUtil;
import com.hd.shopdemo.utils.TextUtil;
import com.hd.shopdemo.utils.status_bar_utils.StatusBarUtil;
import com.ms.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.ll_home_root)
    LinearLayout ll_home_root;
    @BindView(R.id.rcv_homecenter)
    RecyclerView rcv_homecenter;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    HomeContentAdapter homeContentAdapter;
    GridLayoutManager gridLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private int statusBarBgColor;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatusBarUtil.setPaddingSmart(mActivity, ll_home_root);
        statusBarBgColor = StatusBarUtil.getStatusBarBg(mActivity);
        initViews();
        initDatas();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.getmPrevious() == 0)
            StatusBarUtil.immersive(mActivity, statusBarBgColor);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            StatusBarUtil.immersive(mActivity, statusBarBgColor);
        } else {

        }
    }

    @Override
    protected void initViews() {
        LogUtil.i("刷新数据：创建热门页");
        gridLayoutManager = new GridLayoutManager(mContext, 10);
        List<HomeCenterItem> homeCenterItems = new ArrayList<>();
        homeContentAdapter = new HomeContentAdapter(mContext, homeCenterItems, HomeCenterItem.HOMECENTER_TYPE_COUNT);
        rcv_homecenter.setLayoutManager(gridLayoutManager);
        homeContentAdapter.setOnChangeStatusBarBg(new HomeContentAdapter.OnChangeStatusBarBg() {
            @Override
            public void changeStatusBarBg(String bgColor) {
                //onChangeStatusBarBg.changeStatusBarBg(bgColor);
            }
        });

        rcv_homecenter.setAdapter(homeContentAdapter);
        rcv_homecenter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
                LogUtil.i("滑动事件监听：" + firstVisibleItemPosition);
                if (firstVisibleItemPosition == 0) {
                    // playBanner(true);
                } else {
                    // playBanner(false);
                }
            }
        });

        homeContentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //loadHomeBottomShopList(tid, bottomShopPage);
            }
        }, rcv_homecenter);
        swipeLayout.setColorSchemeColors(StatusBarUtil.getStatusBarBg(mActivity));
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        swipeLayout.setRefreshing(true);
    }

    private Banner homeBanner;

    private void stopRefresh() {
        homeContentAdapter.setEnableLoadMore(true);
        swipeLayout.setRefreshing(false);
        if (homeContentAdapter.getData().size() > 0 && homeContentAdapter.getData().get(0).getItemType() == HomeCenterItem.HOMECENTER_HOME_BANNER)
            homeBanner = (Banner) homeContentAdapter.getViewByPosition(rcv_homecenter, 0, R.id.banner);
        if (homeBanner != null)
            LogUtil.i("BannerView hashCode: " + homeBanner.hashCode());
        LogUtil.i("首页banner  ：成功捕获Banner" + homeBanner);
        rcv_homecenter.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtil.i("刷新后，回到顶部");
                rcv_homecenter.scrollToPosition(0);
            }
        }, 100);
    }

    private String img1 = AppConfig.websocketUIR + "/public/images/image_001.jpg";

    @Override
    protected void initDatas() {
        refreshData();
    }

    private void refreshData() {
        List<HomeContentBean.BannerData> bannerDataList = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            bannerDataList.add(new HomeContentBean.BannerData(img1, "#483861", 0, 0, 0));

        List<HomeContentBean.ClassifyData> classifyDataList = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            classifyDataList.add(new HomeContentBean.ClassifyData(img1, "标题" + i, 0, 0, 0));

        String unionTitleImg = img1;
        String unionTitleBgColor = "#53aa76";
        List<HomeContentBean.UnionClassifyData> unionClassifyDataList = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            unionClassifyDataList.add(new HomeContentBean.UnionClassifyData(img1, "标题" + i, 0, 0, 0));

        List<HomeContentBean.UnionItemData> unionItemDataList = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            unionItemDataList.add(new HomeContentBean.UnionItemData(img1, "标题" + i, 0, 0, 0));

        String singleItem_1_TitleImg = img1;
        List<HomeContentBean.SingleItem_1_Data> singleItem_1_DataList = new ArrayList<>();
        for (int i = 0; i < 3; i++)
            singleItem_1_DataList.add(new HomeContentBean.SingleItem_1_Data(img1, "标题" + i, 0, 0, 0));

        String singleItem_2_TitleImg = img1;
        List<HomeContentBean.SingleItem_2_Data> singleItem_2_DataList = new ArrayList<>();
        for (int i = 0; i < 2; i++)
            singleItem_2_DataList.add(new HomeContentBean.SingleItem_2_Data(img1, "标题" + i, 0, 0, 0));

        HomeContentBean homeContentBean = new HomeContentBean();
        homeContentBean.setBannerDataList(bannerDataList);
        homeContentBean.setClassifyDataList(classifyDataList);
        homeContentBean.setUnionTitleImg(unionTitleImg);
        homeContentBean.setUnionTitleBgColor(unionTitleBgColor);
        homeContentBean.setUnionClassifyDataList(unionClassifyDataList);
        homeContentBean.setUnionItemDataList(unionItemDataList);
        homeContentBean.setSingleItem_1_TitleImg(singleItem_1_TitleImg);
        homeContentBean.setSingleItem_1_DataList(singleItem_1_DataList);
        homeContentBean.setSingleItem_2_TitleImg(singleItem_2_TitleImg);
        homeContentBean.setSingleItem_2_DataList(singleItem_2_DataList);

        setAdapterData(homeContentBean);
    }

    public void setAdapterData(HomeContentBean homeContentBean) {
        //添加Banner
        List<HomeContentBean.BannerData> bannerDataList = homeContentBean.getBannerDataList();
        String jsonStr = new Gson().toJson(homeContentBean);
        LogUtil.i(jsonStr);
        List<CustomData> homeBannerList = new ArrayList<>();
        for (int i = 0; i < bannerDataList.size(); i++) {
            HomeContentBean.BannerData bannerData = bannerDataList.get(i);
            String img = bannerData.getImg();
            String backColor = bannerData.getBgColor();
            String type = "" + bannerData.getType();
            String gid = "" + bannerData.getGid();
            String sid = "" + bannerData.getSid();
            homeBannerList.add(new CustomData(img, "", backColor, false, type, gid, sid, ""));
        }
        List<HomeCenterItem> swepBannerDataList = new ArrayList<>();
        swepBannerDataList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_BANNER, 10, homeBannerList));
        homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_BANNER, swepBannerDataList);

        //添加分类
        List<HomeContentBean.ClassifyData> classifyDataList = homeContentBean.getClassifyDataList();
        List<HomeCenterItem> swepClassifyItemList = new ArrayList<>();
        for (int i = 0; i < classifyDataList.size(); i++) {
            HomeContentBean.ClassifyData classifyData = classifyDataList.get(i);
            String imgUrl = classifyData.getImg();
            String title = classifyData.getTitle();
            HomeCenterItem homeCenterItem = new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_CLASSIFY, 2, imgUrl, title);
            homeCenterItem.setData_1("" + classifyData.getGid());
            homeCenterItem.setData_2("" + classifyData.getSid());
            swepClassifyItemList.add(homeCenterItem);
        }
        homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_CLASSIFY, swepClassifyItemList);

        String unionTitleImg = homeContentBean.getUnionTitleImg();
        String unionTitleBgColor = homeContentBean.getUnionTitleBgColor();
        List<HomeContentBean.UnionClassifyData> unionClassifyDataList = homeContentBean.getUnionClassifyDataList();
        List<HomeContentBean.UnionItemData> unionItemDataList = homeContentBean.getUnionItemDataList();

        boolean topRounded_Classify = true;
        boolean topRounded_Item = true;
        boolean bottomRounded = true;
        //套餐标题
        List<HomeCenterItem> swepUnionTitleList = new ArrayList<>();
        if (unionClassifyDataList != null && unionClassifyDataList.size() > 0 && unionItemDataList != null && unionItemDataList.size() > 0) {
            if (!TextUtil.isEmpty(unionTitleImg)) {
                swepUnionTitleList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_UNION_TITLE, 10, unionTitleImg, "", unionTitleBgColor));
                topRounded_Classify = false;
                topRounded_Item = false;
            }
        }
        homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_UNION_TITLE, swepUnionTitleList);
        //套餐分类
        List<HomeCenterItem> swepUnionClassifyDataList = new ArrayList<>();
        if (unionClassifyDataList != null && unionClassifyDataList.size() > 0) {
            if (unionItemDataList != null && unionItemDataList.size() > 0) {
                topRounded_Item = false;
                bottomRounded = false;
            }
            if ((unionClassifyDataList.size() % 2) == 1)//如果是单数，最后要补上一个
                unionClassifyDataList.add(new HomeContentBean.UnionClassifyData());
            for (int i = 0; i < unionClassifyDataList.size(); i++) {
                HomeContentBean.UnionClassifyData unionClassifyData = unionClassifyDataList.get(i);
                if (unionClassifyDataList.size() <= 2) {//如果总共就两个，那么上下都需要考虑直角
                    swepUnionClassifyDataList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_UNION_CLASSIFY, 5, i, unionClassifyData, unionTitleBgColor, topRounded_Classify, bottomRounded));
                } else if (i == 0 || i == 1) {//否则的话顶部考虑直角
                    swepUnionClassifyDataList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_UNION_CLASSIFY, 5, i, unionClassifyData, unionTitleBgColor, topRounded_Classify, false));
                } else if (i == unionClassifyDataList.size() - 1 || i == unionClassifyDataList.size() - 2) {//否则的话底部考虑直角
                    swepUnionClassifyDataList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_UNION_CLASSIFY, 5, i, unionClassifyData, unionTitleBgColor, false, bottomRounded));
                } else
                    swepUnionClassifyDataList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_UNION_CLASSIFY, 5, i, unionClassifyData, unionTitleBgColor, false, false));
            }
        }
        homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_UNION_CLASSIFY, swepUnionClassifyDataList);
        //联盟卡套餐
        List<HomeCenterItem> swepUnionItemList = new ArrayList<>();
        if (unionItemDataList != null && unionItemDataList.size() > 0) {
            for (int i = 0; i < unionItemDataList.size(); i++) {
                if (i == 0)
                    swepUnionItemList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_UNION_ITEM, 10, i, unionItemDataList.get(i), topRounded_Item, unionTitleBgColor));
                else
                    swepUnionItemList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_UNION_ITEM, 10, i, unionItemDataList.get(i), true, unionTitleBgColor));
            }
        }
        homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_UNION_ITEM, swepUnionItemList);

        //单列信息一
        String singleItem_1_TitleImg = homeContentBean.getSingleItem_1_TitleImg();
        List<HomeContentBean.SingleItem_1_Data> singleItem_1_DataList = homeContentBean.getSingleItem_1_DataList();
        List<HomeCenterItem> swepSingleItem_1_List = new ArrayList<>();
        if (singleItem_1_DataList != null && singleItem_1_DataList.size() > 0) {
            swepSingleItem_1_List.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_SINGLE_TITLE, 10, singleItem_1_TitleImg, ""));
            for (int i = 0; i < singleItem_1_DataList.size(); i++)
                swepSingleItem_1_List.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_SINGLE_ITEM_1, singleItem_1_DataList.get(i)));
        }
        homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_SINGLE_ITEM_1, swepSingleItem_1_List);

        //单列信息二
        String singleItem_2_TitleImg = homeContentBean.getSingleItem_2_TitleImg();
        List<HomeContentBean.SingleItem_2_Data> singleItem_2_DataList = homeContentBean.getSingleItem_2_DataList();
        List<HomeCenterItem> swepSingleItem_2_List = new ArrayList<>();
        if (singleItem_2_DataList != null && singleItem_2_DataList.size() > 0) {
            swepSingleItem_2_List.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_SINGLE_TITLE, 10, singleItem_2_TitleImg, ""));
            for (int i = 0; i < singleItem_2_DataList.size(); i++)
                swepSingleItem_2_List.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_SINGLE_ITEM_2, singleItem_2_DataList.get(i)));
        }
        homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_SINGLE_ITEM_2, swepSingleItem_2_List);

        stopRefresh();
    }
}
