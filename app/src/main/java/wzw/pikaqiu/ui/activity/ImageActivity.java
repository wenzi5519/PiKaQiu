package wzw.pikaqiu.ui.activity;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import wzw.pikaqiu.R;
import wzw.pikaqiu.ui.base.BaseActivity;

public class ImageActivity extends BaseActivity {

    @Bind(R.id.bag)
    ImageView bag;

    @Override
    protected void initData() {
        super.initData();
        fullScreen();
        String url = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(url)) {
            Glide.with(this)
                    .load(url)
                    .centerCrop()
                    .into(bag);
        }
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_image;
    }
}
