package wzw.pikaqiu.ui.activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import wzw.pikaqiu.R;
import wzw.pikaqiu.presenter.LoginPresenterCompl;
import wzw.pikaqiu.presenter.ipresenter.ILoginPresenter;
import wzw.pikaqiu.ui.base.BaseActivity;
import wzw.pikaqiu.ui.view.ILoginView;
import wzw.pikaqiu.ui.widget.MyEditText;

public class LoginActivity extends BaseActivity implements ILoginView {

    @Bind(R.id.edt_name)
    MyEditText edtName;
    @Bind(R.id.edt_password)
    MyEditText edtPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.che_save_pas)
    CheckBox cheSavePas;
    @Bind(R.id.text_help)
    TextView textHelp;

    private ILoginPresenter loginPresenter;
    private Context context;
    private ProgressDialog dialog;
    private SharedPreferences preferences;

    @Override
    public int getContentViewId() {
        return autoLogin();
    }

    @Override
    protected void setOnClick() {
        btnLogin.setOnClickListener(this);
        textHelp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                btnLogin.setClickable(false);
                btnLogin.setBackgroundResource(R.color.gray);
                loginPresenter.setProgressBarVisiblity(View.VISIBLE);
                String userNameStr = edtName.getText().toString();
                String passwordStr = edtPassword.getText().toString();
                loginPresenter.doLogin(userNameStr, passwordStr);
                break;
            case R.id.text_help:
                setDialog();
                break;
            default:
                break;
        }

    }
    private void setDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle(getResources().getString(R.string.prompt));
        builder.setMessage(getResources().getString(R.string.help)); //设置内容
        builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
            }
        });
        builder.create().show();
    }
    private int autoLogin() {
        preferences = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        if (preferences.getBoolean("AUTO_ISCHECK", false)) {
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            this.finish();
        } else {
            return R.layout.activity_login;
        }
        return R.layout.activity_login;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {
        colorChange(getResources().getColor(R.color.white));
        context = LoginActivity.this;
        loginPresenter = new LoginPresenterCompl(this);
        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage(getResources().getString(R.string.loading));
        loginPresenter.setProgressBarVisiblity(View.GONE);
        edtName.setText(preferences.getString("userName", ""));//默认记住用户名
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onLoginResult(Boolean result, int code) {
        loginPresenter.setProgressBarVisiblity(View.GONE);
        if (result) {
            btnLogin.setClickable(true);
            btnLogin.setBackgroundResource(R.color.blue);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("USER_NAME", edtName.getText().toString());
            if (cheSavePas.isChecked()) {
                editor.putString("PASSWORD", edtPassword.getText().toString());
                editor.putBoolean("AUTO_ISCHECK", true).commit();
            }
            editor.commit();
            Intent intent = new Intent(context, HomeActivity.class);
            Toast.makeText(this, getResources().getString(R.string.loading_success), Toast.LENGTH_SHORT).show();
            startActivity(intent);
            LoginActivity.this.finish();
        } else {
            btnLogin.setClickable(true);
            btnLogin.setBackgroundResource(R.color.blue);
            Toast.makeText(this, getResources().getString(R.string.loading_failt), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        if (visibility == View.GONE) {
            dialog.dismiss();
        }
        if (visibility == View.VISIBLE) {
            dialog.show();
        }
    }
}
