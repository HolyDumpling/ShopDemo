package com.hd.shopdemo.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.hd.shopdemo.MainActivity;
import com.hd.shopdemo.R;
import com.hd.shopdemo.app.AppConfig;
import com.hd.shopdemo.base.BaseFragment;
import com.hd.shopdemo.bean.BaseBean;
import com.hd.shopdemo.bean.CustomData;
import com.hd.shopdemo.ui.home.HomeCenterItem;
import com.hd.shopdemo.ui.home.HomeContentAdapter;
import com.hd.shopdemo.ui.home.bean.HomeBottomGoodsItemBean;
import com.hd.shopdemo.ui.home.bean.HomeCenterItemBean;
import com.hd.shopdemo.utils.ImageUtil;
import com.hd.shopdemo.utils.LogUtil;
import com.hd.shopdemo.utils.TextUtil;
import com.hd.shopdemo.utils.retrofit_utils.RetrofitUtil;
import com.hd.shopdemo.utils.status_bar_utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.ll_home_root)
    LinearLayout ll_home_root;
    @BindView(R.id.rl_nav)
    RelativeLayout rl_nav;
    @BindView(R.id.top_ruler)
    View top_ruler;
    @BindView(R.id.rcv_homecenter)
    RecyclerView rcv_homecenter;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;


    @BindView(R.id.et_search_key_1)
    EditText et_search_key_1;
    @BindView(R.id.iv_headImg)
    ImageView iv_headImg;

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
        if (MainActivity.getmPrevious() == 0) {
            StatusBarUtil.immersive(mActivity, statusBarBgColor);
            rl_nav.setBackgroundColor(statusBarBgColor);
            playBanner(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        playBanner(false);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        LogUtil.i("切换页面，首页：" + hidden);
        if (!hidden) {
            StatusBarUtil.immersive(mActivity, statusBarBgColor);
            rl_nav.setBackgroundColor(statusBarBgColor);
            playBanner(true);
        } else {
            playBanner(false);
        }
    }

    int top_rulerWidth_min = 0;
    int top_rulerWidth_max = 0;

    @Override
    protected void initViews() {
        LogUtil.i("刷新数据：创建热门页");
        int dp_45 = ImageUtil.dip2px(mContext, 45);
        int dp_30 = ImageUtil.dip2px(mContext, 30);
        int dp_20 = ImageUtil.dip2px(mContext, 20);
        int dp_5 = ImageUtil.dip2px(mContext, 5);

        top_ruler.post(new Runnable() {
            @Override
            public void run() {
                top_rulerWidth_min = top_ruler.getMeasuredWidth();
            }
        });
        et_search_key_1.post(new Runnable() {
            @Override
            public void run() {
                top_rulerWidth_max = et_search_key_1.getMeasuredWidth();
            }
        });
        gridLayoutManager = new GridLayoutManager(mContext, 10);
        List<HomeCenterItem> homeCenterItems = new ArrayList<>();
        homeContentAdapter = new HomeContentAdapter(mContext, homeCenterItems, HomeCenterItem.HOMECENTER_TYPE_COUNT);
        rcv_homecenter.setLayoutManager(gridLayoutManager);
        rl_nav.post(new Runnable() {
            @Override
            public void run() {
                List<HomeCenterItem> swepBannerDataList = new ArrayList<>();
                swepBannerDataList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_TOP_PLACEHOLDER, rl_nav.getMeasuredHeight()));
                homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_TOP_PLACEHOLDER, swepBannerDataList);
            }
        });
        homeContentAdapter.setOnChangeStatusBarBg(new HomeContentAdapter.OnChangeStatusBarBg() {
            @Override
            public void changeStatusBarBg(int bgColor) {
                statusBarBgColor = bgColor;
                rl_nav.setBackgroundColor(bgColor);
                StatusBarUtil.immersive(mActivity, bgColor);
            }
        });
        RelativeLayout.LayoutParams rllp_et_search_key_1 = (RelativeLayout.LayoutParams) et_search_key_1.getLayoutParams();

        rcv_homecenter.setAdapter(homeContentAdapter);
        rcv_homecenter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int newTopMargin = rllp_et_search_key_1.topMargin - dy;
                LogUtil.i("滑动事件坐标监听：" + dp_45 + "，" + dy + "，" + newTopMargin);
                if (newTopMargin <= dp_5) {
                    rllp_et_search_key_1.topMargin = dp_5;
                    iv_headImg.setAlpha((float) 0);
                    rllp_et_search_key_1.width = top_rulerWidth_min;
                } else if (newTopMargin >= dp_45) {
                    rllp_et_search_key_1.topMargin = dp_45;
                    iv_headImg.setAlpha((float) 1);
                    rllp_et_search_key_1.width = top_rulerWidth_max;
                } else {
                    rllp_et_search_key_1.topMargin = newTopMargin;
                    double p = 1.0 - ((dp_45 * 1.0 - newTopMargin) / dp_20);
                    iv_headImg.setAlpha((float) p);
                    int subWidth = (int) (top_rulerWidth_min + (top_rulerWidth_max - top_rulerWidth_min) * p);
                    if (subWidth <= top_rulerWidth_min)
                        subWidth = top_rulerWidth_min;
                    rllp_et_search_key_1.width = subWidth;
                }
                et_search_key_1.setLayoutParams(rllp_et_search_key_1);

                int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
                LogUtil.i("滑动事件监听：" + firstVisibleItemPosition);
                if (firstVisibleItemPosition <= 1) {
                    playBanner(true);
                } else {
                    playBanner(false);
                }
            }
        });

        homeContentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadHomeBottomGoodsList();
            }
        }, rcv_homecenter);
        swipeLayout.setColorSchemeColors(StatusBarUtil.getStatusBarBg(mActivity));
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initDatas();
            }
        });
        swipeLayout.setRefreshing(true);
    }


    private void stopRefresh() {
        homeContentAdapter.setEnableLoadMore(true);
        swipeLayout.setRefreshing(false);
        rcv_homecenter.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtil.i("刷新后，回到顶部");
                rcv_homecenter.scrollToPosition(0);
            }
        }, 100);
    }


    public void playBanner(boolean play) {
        if (gridLayoutManager != null) {
            if (play) {
                if (gridLayoutManager.findFirstVisibleItemPosition() <= 1) {
                    homeContentAdapter.startBanner();
                }
            } else {
                homeContentAdapter.stopBanner();
            }
        }
    }

    private String img1 = AppConfig.APP_SERVER_ADDRESS + "/public/images/image_001.jpg";

    @Override
    protected void initDatas() {
        loadHomeCenterData();
        page = 1;
        loadHomeBottomGoodsList();
    }

    String[] bgColors = {"#a15949", "#57b3f0", "#73c037", "#78a0a0", "#03a28a", "#6a73c8", "#756315"};
    String[] banners = {
            "http://onecdndev.baozhen100.com/seller/201911/f8b0e27fd4f54907beb565870edc4d53.jpg",
            "http://onecdndev.baozhen100.com/seller/201911/ee7e2cb4a88d405bb7f5b613c58621de.jpg",
            "http://onecdndev.baozhen100.com/seller/201911/81f1f7e835b64f2cb7e9dbbb80504821.png",
            "http://onecdndev.baozhen100.com/seller/201912/5b35bbb84d3542d3bb56ab6002dbfe15.jpg",
            "http://onecdndev.baozhen100.com/seller/201911/0b454b2ee1cb40d890c6107edbf0814a.png",
            "http://onecdndev.baozhen100.com/seller/201911/11b515f494d94167916591ee9e955624.png",
            "http://onecdndev.baozhen100.com/seller/201908/c4143947f2964294a41afe3ddf1301b3.jpg"
    };

    public void setAdapterData(HomeCenterItemBean homeContentBean) {
        goods_headImg = homeContentBean.getGoods_head();
        //添加Banner
        List<HomeCenterItemBean.BannerDataListBean> bannerDataList = homeContentBean.getBannerDataList();
        String jsonStr = new Gson().toJson(homeContentBean);
        LogUtil.i("打印数据：" + jsonStr);
        List<CustomData> homeBannerList = new ArrayList<>();
/*
        for (int i = 0; i < bannerDataList.size(); i++) {
            HomeCenterItemBean.BannerDataListBean bannerData = bannerDataList.get(i);
            String img = bannerData.getS_img();
            String backColor = bannerData.getBgColor();
            String type = "" + bannerData.getType();
            String gid = "" + bannerData.getG_id();
            String sid = "" + bannerData.getS_id();
            homeBannerList.add(new CustomData(img, "", backColor, false, type, gid, sid, ""));
        }
*/

        for (int i = 0; i < 3; i++) {
            HomeCenterItemBean.BannerDataListBean bannerData = bannerDataList.get(i);
            String img = banners[i];
            String backColor = bgColors[i];
            String type = "" + bannerData.getType();
            String gid = "" + bannerData.getG_id();
            String sid = "" + bannerData.getS_id();
            homeBannerList.add(new CustomData(img, "", backColor, false, type, gid, sid, ""));
        }
        List<HomeCenterItem> swepBannerDataList = new ArrayList<>();
        swepBannerDataList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_BANNER, 10, homeBannerList));
        homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_BANNER, swepBannerDataList);


        //添加分类
        List<HomeCenterItemBean.ClassifyDataListBean> classifyDataList = homeContentBean.getClassifyDataList();
        List<HomeCenterItem> swepClassifyItemList = new ArrayList<>();
        for (int i = 0; i < classifyDataList.size(); i++) {
            HomeCenterItemBean.ClassifyDataListBean classifyData = classifyDataList.get(i);
            String imgUrl = classifyData.getS_img();
            String title = classifyData.getS_title();
            HomeCenterItem homeCenterItem = new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_CLASSIFY, 2, imgUrl, title);
            homeCenterItem.setData_1("" + classifyData.getG_id());
            homeCenterItem.setData_2("" + classifyData.getS_id());
            swepClassifyItemList.add(homeCenterItem);
        }
        homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_CLASSIFY, swepClassifyItemList);

        String unionTitleImg = homeContentBean.getUnionTitleImg();
        String unionTitleBgColor = homeContentBean.getUnionTitleBgColor();
        List<HomeCenterItemBean.UnionClassifyDataListBean> unionClassifyDataList = homeContentBean.getUnionClassifyDataList();
        List<HomeCenterItemBean.UnionItemDataListBean> unionItemDataList = homeContentBean.getUnionItemDataList();

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
                unionClassifyDataList.add(new HomeCenterItemBean.UnionClassifyDataListBean());
            for (int i = 0; i < unionClassifyDataList.size(); i++) {
                HomeCenterItemBean.UnionClassifyDataListBean unionClassifyData = unionClassifyDataList.get(i);
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
        List<HomeCenterItemBean.SingleItem1DataListBean> singleItem_1_DataList = homeContentBean.getSingleItem_1_dataList();
        List<HomeCenterItem> swepSingleItem_1_List = new ArrayList<>();
        if (singleItem_1_DataList != null && singleItem_1_DataList.size() > 0) {
            swepSingleItem_1_List.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_SINGLE_TITLE, 10, singleItem_1_TitleImg, ""));
            for (int i = 0; i < singleItem_1_DataList.size(); i++)
                swepSingleItem_1_List.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_SINGLE_ITEM_1, singleItem_1_DataList.get(i)));
        }
        homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_SINGLE_ITEM_1, swepSingleItem_1_List);

        //单列信息二
        String singleItem_2_TitleImg = homeContentBean.getSingleItem_2_TitleImg();
        List<HomeCenterItemBean.SingleItem2DataListBean> singleItem_2_DataList = homeContentBean.getSingleItem_2_dataList();
        List<HomeCenterItem> swepSingleItem_2_List = new ArrayList<>();
        if (singleItem_2_DataList != null && singleItem_2_DataList.size() > 0) {
            swepSingleItem_2_List.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_SINGLE_TITLE, 10, singleItem_2_TitleImg, ""));
            for (int i = 0; i < singleItem_2_DataList.size(); i++)
                swepSingleItem_2_List.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_SINGLE_ITEM_2, singleItem_2_DataList.get(i)));
        }
        homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_SINGLE_ITEM_2, swepSingleItem_2_List);

    }

    private int page = 1;
    private static int GOODS_MAX_ROWS = 20;
    private int bottomGoodsCount = 0;

    private boolean bottomGoodsLoading = false;

    private String goods_headImg = "";

    private void loadHomeBottomGoodsList() {
        if (bottomGoodsLoading)
            return;
        bottomGoodsLoading = true;

        RetrofitUtil.getInstance().getHomeBottomGoodsItemBean("" + page, "" + GOODS_MAX_ROWS, new Callback<BaseBean<HomeBottomGoodsItemBean>>() {
            @Override
            public void onResponse(@Nullable Call<BaseBean<HomeBottomGoodsItemBean>> call, @Nullable Response<BaseBean<HomeBottomGoodsItemBean>> response) {
                LogUtil.i("首页底部商品数据   req：" + new Gson().toJson(response));
                if (response != null) {
                    BaseBean<HomeBottomGoodsItemBean> bean = response.body();
                    if (bean != null) {
                        if (bean.getCode() == 200) {
                            LogUtil.i("打印下标  page=" + page);
                            if (page == 1) {
                                bottomGoodsCount = 0;
                                List<HomeCenterItem> homeCenterItemList = new ArrayList<>();
                                homeCenterItemList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_DOUBLE_TITLE, 10, goods_headImg, ""));
                                for (HomeBottomGoodsItemBean.ItemsBean item : bean.getData().getItemList()) {
                                    homeCenterItemList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_DOUBLE_ITEM, 5, bottomGoodsCount, item));
                                    bottomGoodsCount++;
                                }
                                homeContentAdapter.setCenterDataItem(HomeCenterItem.HOMECENTER_HOME_DOUBLE_ITEM, homeCenterItemList);
                                stopRefresh();
                            } else {
                                List<HomeCenterItem> homeCenterItemList = new ArrayList<>();
                                for (HomeBottomGoodsItemBean.ItemsBean item : bean.getData().getItemList()) {
                                    homeCenterItemList.add(new HomeCenterItem(HomeCenterItem.HOMECENTER_HOME_DOUBLE_ITEM, 5, bottomGoodsCount, item));
                                    bottomGoodsCount++;
                                }
                                homeContentAdapter.addBottomDataIndex(HomeCenterItem.HOMECENTER_HOME_DOUBLE_ITEM, homeCenterItemList);
                            }
                            page++;
                            if (bean.getData().getItemCount() < GOODS_MAX_ROWS)
                                homeContentAdapter.loadMoreEnd();
                            else
                                homeContentAdapter.loadMoreComplete();
                        } else {
                            homeContentAdapter.loadMoreFail();
                            Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } else
                        homeContentAdapter.loadMoreFail();
                } else
                    homeContentAdapter.loadMoreFail();
                bottomGoodsLoading = false;
            }

            @Override
            public void onFailure(@Nullable Call<BaseBean<HomeBottomGoodsItemBean>> call, @Nullable Throwable t) {
                LogUtil.e("加载首页底部商品出现问题：" + t.toString());
                homeContentAdapter.loadMoreFail();
                bottomGoodsLoading = false;
            }
        });
    }

    private boolean homeCenterDataLoading = false;


    private void loadHomeCenterData() {
        if (homeCenterDataLoading)
            return;
        homeCenterDataLoading = true;

        RetrofitUtil.getInstance().getHomeCenterItemBean(new Callback<BaseBean<HomeCenterItemBean>>() {
            @Override
            public void onResponse(@Nullable Call<BaseBean<HomeCenterItemBean>> call, @Nullable Response<BaseBean<HomeCenterItemBean>> response) {
                LogUtil.i("首页内容数据   req：" + new Gson().toJson(response));
                if (response != null) {
                    BaseBean<HomeCenterItemBean> bean = response.body();
                    if (bean != null) {
                        if (bean.getCode() == 200) {
                            setAdapterData(bean.getData());
                        } else {
                            Toast.makeText(mContext, bean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                homeCenterDataLoading = false;
                stopRefresh();
            }

            @Override
            public void onFailure(@Nullable Call<BaseBean<HomeCenterItemBean>> call, @Nullable Throwable t) {
                LogUtil.e("加载首页内容出现问题：" + t.toString());
                homeCenterDataLoading = false;
                stopRefresh();
            }
        });
    }


}
