package wzw.pikaqiu.ui.fragment;


import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import wzw.pikaqiu.R;
import wzw.pikaqiu.adapter.WidgetListAdapter;
import wzw.pikaqiu.model.Item;
import wzw.pikaqiu.net.Api;
import wzw.pikaqiu.net.NetWork;
import wzw.pikaqiu.ui.activity.ImageActivity;
import wzw.pikaqiu.ui.base.BaseFragment;
import wzw.pikaqiu.ui.base.OnItemClickListener;
import wzw.pikaqiu.utils.GankBeautyResultToItemsMapper;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.rec_list)
    RecyclerView recList;
    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private WidgetListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        swipeRefresh.setColorSchemeColors(R.color.colorPrimaryDark,
                R.color.colorPrimary, R.color.blue);
        swipeRefresh.setOnRefreshListener(this);
        recList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new WidgetListAdapter(null);
        recList.setAdapter(adapter);
        onRefresh();
    }

    @Override
    protected void setOnClick() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, Object item) {
                Intent intent = new Intent(getActivity(), ImageActivity.class);
                intent.putExtra("url", ((Item) item).imageUrl);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        subscription = NetWork.getNetDate(Api.DB_BREAST)
                .getBeauties(20, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(GankBeautyResultToItemsMapper.getInstance())
                .subscribe(new Action1<List<Item>>() {
                    @Override
                    public void call(List<Item> items) {
                        adapter.setList(items);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
        swipeRefresh.setRefreshing(false);
    }
}
