package wzw.pikaqiu.presenter.ipresenter;

public interface ILoginPresenter {
    void doLogin(String name, String passwd);

    void setProgressBarVisiblity(int visiblity);
}