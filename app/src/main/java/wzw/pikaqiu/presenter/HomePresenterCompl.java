package wzw.pikaqiu.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import wzw.pikaqiu.presenter.ipresenter.IHomePresenter;
import wzw.pikaqiu.ui.view.IHomeView;


/**
 * Created by wenzi on 2016/5/12.
 */
public class HomePresenterCompl implements IHomePresenter {
    private IHomeView iHomeView;
    private Fragment mContent;
    private FragmentManager fragmentManager;

    public HomePresenterCompl(FragmentManager fragmentManager, IHomeView iHomeView) {
        this.iHomeView = iHomeView;
        this.fragmentManager=fragmentManager;
    }

    @Override
    public void setFragmentSwitch(Fragment from, Fragment to) {
        iHomeView.onSetFragmentSwitch(from,to);
    }


}
