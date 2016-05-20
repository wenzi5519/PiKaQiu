package wzw.pikaqiu.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * Created by 子文 on 2015/12/11.
 * Fragment 的基类
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected View view;
    protected Subscription subscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(getLayoutId(), null, false);
            //使用注解
            ButterKnife.bind(this, view);
            initData();
            setOnClick();
            httpRequest();
        }
        ViewGroup parent = (ViewGroup) view.getParent();

        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    protected abstract int getLayoutId();

    /**
     * 设置监听
     */
    protected void setOnClick() {

    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    protected void httpRequest() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
