package wzw.pikaqiu.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import wzw.pikaqiu.R;
import wzw.pikaqiu.adapter.DateAdapter;
import wzw.pikaqiu.model.Date;
import wzw.pikaqiu.ui.base.BaseFragment;

/**
 * Created by Administrator on 2016/5/12.
 */
public class DateFragment extends BaseFragment {
    @Bind(R.id.rec_list_date)
    RecyclerView date;

    private List list;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_date;
    }

    @Override
    protected void setOnClick() {

    }

    @Override
    protected void initData() {
        super.initData();
        fake();
        date.setLayoutManager(new LinearLayoutManager(getActivity()));
        DateAdapter adapter = new DateAdapter(list);
        date.setAdapter(adapter);
    }

    private void fake() {
        list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            Date date = new Date();
            date.setTitle("标题" + i);
            List imgList = new ArrayList();
            for (int j = 0; j < 5; j++) {
                Date.ImageEntity imageEntity = new Date.ImageEntity();
                imageEntity.setName("图片" + j);
                imageEntity.setUrl("http://imgsrc.baidu.com/forum/pic/item/87116e061d950a7b19cc40040bd162d9f3d3c90a.jpg");
                imgList.add(imageEntity);
            }
            date.setList(imgList);
            list.add(date);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
