package wzw.pikaqiu.presenter;

import android.os.Handler;
import android.os.Looper;

import wzw.pikaqiu.model.UserModel;
import wzw.pikaqiu.model.imodle.IUser;
import wzw.pikaqiu.presenter.ipresenter.ILoginPresenter;
import wzw.pikaqiu.ui.view.ILoginView;


public class LoginPresenterCompl implements ILoginPresenter {
    ILoginView iLoginView;
    IUser user;
    Handler handler;

    public LoginPresenterCompl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        initUser();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void doLogin(String name, String passwd) {
        Boolean isLoginSuccess = true;
        final int code = user.checkUserValidity(name, passwd);
        if (code != 0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLoginView.onLoginResult(result, code);
            }
        }, 500);
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iLoginView.onSetProgressBarVisibility(visiblity);
    }

    private void initUser() {
        user = new UserModel("admin", "admin");
    }
}