package wzw.pikaqiu.ui.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.Bind;
import wzw.pikaqiu.R;
import wzw.pikaqiu.ui.base.BaseActivity;
import wzw.pikaqiu.ui.fragment.DailyFragment;
import wzw.pikaqiu.ui.fragment.DateFragment;
import wzw.pikaqiu.ui.fragment.HomeFragment;
import wzw.pikaqiu.ui.fragment.MineFragment;

public class HomeActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.flayout_fragment)
    RelativeLayout flayoutFragment;
    @Bind(R.id.rbtn_home)
    RadioButton rbtnHome;
    @Bind(R.id.rbtn_daily)
    RadioButton rbtnDaily;
    @Bind(R.id.rbtn_date)
    RadioButton rbtnDate;
    @Bind(R.id.rbtn_mine)
    RadioButton rbtnMine;
    @Bind(R.id.group_bottom)
    RadioGroup groupBottom;

    private Fragment mContent;
    private HomeFragment homeFragment;
    private DailyFragment payFragment;
    private DateFragment dateFragment;
    private MineFragment mineFragment;
    private PopupWindow popupWindow;
    private long mExitTime;

    @Override
    protected void newFragment() {
        homeFragment = new HomeFragment();
        payFragment = new DailyFragment();
        dateFragment = new DateFragment();
        mineFragment = new MineFragment();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected void setOnClick() {
        groupBottom.setOnCheckedChangeListener(checkedChangeListener());
        toolbar.setOnMenuItemClickListener(menuListener());
    }

    @NonNull
    private Toolbar.OnMenuItemClickListener menuListener() {
        return new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ab_search:
                        Toast.makeText(HomeActivity.this, "ab_search", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_exit:
                        getPopupWindow();
                        popupWindow.showAsDropDown(findViewById(R.id.action_exit));
                        break;
                    default:
                        break;
                }
                return true;
            }
        };
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void initData() {
        toolbar.setTitle(getResources().getString(R.string.bar_title));// 标题的文字需在setSupportActionBar之前，不然会无效
        toolbar.setSubtitle(getResources().getString(R.string.fighting));//副标题
        setSupportActionBar(toolbar);
        mContent = homeFragment;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.flayout_fragment, homeFragment, homeFragment.getClass().getName())
                .commit();
        rbtnHome.setChecked(true);
        colorChange(getResources().getColor(R.color.white));
    }

    @NonNull
    private RadioGroup.OnCheckedChangeListener checkedChangeListener() {
        return new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment = null;
                switch (checkedId) {
                    case R.id.rbtn_home:
                        fragment = homeFragment;
                        break;
                    case R.id.rbtn_daily:
                        fragment = payFragment;
                        break;
                    case R.id.rbtn_date:
                        fragment = dateFragment;
                        break;
                    case R.id.rbtn_mine:
                        fragment = mineFragment;
                        break;
                    default:
                        break;
                }
                switchContent(mContent, fragment);
            }
        };
    }

    public void switchContent(Fragment from, Fragment to) {
        if (mContent != to) {
            mContent = to;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.anim_scale_in, R.anim.anim_scale_out);
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.flayout_fragment, to,
                        to.getClass().getName()).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 创建PopupWindow
     */
    protected void initPopuptWindow() {
        View popupWindow_view = getLayoutInflater().inflate(R.layout.popwindows_setting, null,
                false);
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置动画效果
//        popupWindow.setAnimationStyle(R.style.AnimationFade);
        // 点击其他地方消失
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
        popupWindow_view.findViewById(R.id.text_relogin).setOnClickListener(this);
    }

    /***
     * 获取PopupWindow实例
     */
    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopuptWindow();
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.text_relogin:
                SharedPreferences preferences = this
                        .getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("AUTO_ISCHECK", false).commit();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                HomeActivity.this.finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, getResources().getString(R.string.exit), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
