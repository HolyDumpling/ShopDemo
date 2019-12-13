package com.hd.shopdemo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hd.shopdemo.R;
import com.hd.shopdemo.base.BaseFragment;
import com.hd.shopdemo.utils.glide_utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeViewPageContentFragment extends BaseFragment {
    public static final String TID = "TID";
    private String tid = ""; //首页商品分类  id

    HomeContentAdapter homeContentAdapter;
    @BindView(R.id.rcv_homecenter)
    RecyclerView rcv_homecenter;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;


    public static HomeViewPageContentFragment newInstance(String param) {
        HomeViewPageContentFragment fragment = new HomeViewPageContentFragment();
        Bundle args = new Bundle();
        args.putString(TID, param);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.viewpage_homecenter_adapter, container, false);
            ButterKnife.bind(this, rootView);
            //tid = getArguments().getString(TID);
            initViews();
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getContext();
        mActivity = getActivity();
        glideUtils = new GlideUtils(mContext);
        initDatas();
    }

    GridLayoutManager gridLayoutManager;

    @Override
    protected void initViews() {
        /*

        LogUtil.i("刷新数据：创建热门页");
        gridLayoutManager = new GridLayoutManager(mContext,10);
        List<HomeCenterItem> homeCenterItems = new ArrayList<>();
        homeContentAdapter = new HomeContentAdapter(mContext, homeCenterItems,HomeCenterItem.HOMECENTER_TYPE_COUNT);
        rcv_homecenter.setLayoutManager(gridLayoutManager);
        homeContentAdapter.setOnChangeStatusBarBg(new HomeContentAdapter.OnChangeStatusBarBg() {
            @Override
            public void changeStatusBarBg(String bgColor) {
                //onChangeStatusBarBg.changeStatusBarBg(bgColor);
            }
        });

        rcv_homecenter.setAdapter(homeContentAdapter);
        if(tid.equals("0")){
            rcv_homecenter.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
                    LogUtil.i("滑动事件监听："+firstVisibleItemPosition);
                    if(firstVisibleItemPosition==0) {
                       // playBanner(true);
                    } else {
                       // playBanner(false);
                    }
                }
            });
        }

        homeContentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (tid.equals("0")) {
                    //loadHomeBottomShopList(tid, bottomShopPage);
                }else {
                   // loadStoreList(bottomStorePage);
                }
            }
        },rcv_homecenter);
        swipeLayout.setColorSchemeColors(StatusBarUtil.getStatusBarBg(mActivity));
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (tid.equals("0")) {
                    LogUtil.i("数据刷新，首页下拉刷新");
                    //((HomeFragment) (getParentFragment())).initHomeDialog();
                    ((HomeFragment) (getParentFragment())).refreshData();
                } else {
                    LogUtil.i("数据刷新，附加页下拉刷新");
                    //homeCenterItemAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                    initDatas();
                }
            }
        });
        swipeLayout.setRefreshing(true);


        */
    }

    @Override
    protected void initDatas() {

    }

/*
    public void playBanner(boolean play){
        if(homeBanner!=null&&gridLayoutManager!=null){
            if(play&&!homeBanner.isStart()){
                if(gridLayoutManager.findFirstVisibleItemPosition()==0){
                    LogUtil.i("首页banner：成功播放Banner");
                    homeBanner.startAutoPlay();
                }
            }else if(!play&&homeBanner.isStart()){
                LogUtil.i("首页banner：成功暂停Banner");
                homeBanner.stopAutoPlay();
            }
        }else
            LogUtil.i("首页banner：已经是个空的了：播放："+play);
    }

    public void releaseBanner(){
        if(homeBanner!=null){
            LogUtil.i("首页banner  ：成功释放Banner"+homeBanner);
            homeBanner.stopAutoPlay();
            homeBanner.releaseBanner();
            homeBanner = null;
        }else
            LogUtil.i("首页banner  ：已经是个空的了");
    }
*/

}
