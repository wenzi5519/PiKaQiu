package wzw.pikaqiu.ui.activity;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.Bind;
import wzw.pikaqiu.R;
import wzw.pikaqiu.ui.base.BaseActivity;

public class ImageActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.detail_img)
    ImageView imageView;
    private ArrayList list;

    @Override
    protected void initData() {
        super.initData();
        fullScreen();
        setSupportActionBar(toolbar);
        toolbarLayout.setTitle("标题");
        String url = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(url)) {
            Glide.with(this)
                    .load(url)
                    .centerCrop()
                    .into(imageView);
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_image;
    }
}
