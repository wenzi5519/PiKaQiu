package wzw.pikaqiu.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wzw.pikaqiu.R;
import wzw.pikaqiu.model.Date;
import wzw.pikaqiu.ui.base.BaseRecyclerViewAdapter;

/**
 * Created by Administrator on 2016/5/20.
 */
public class DateItemAdapter extends BaseRecyclerViewAdapter<Date.ImageEntity, DateItemAdapter.MyViewHolder> {

    /**
     * @param list the datas to attach the adapter
     */
    public DateItemAdapter(List<Date.ImageEntity> list) {
        super(list);
    }

    @Override
    protected void bindDataToItemView(MyViewHolder myViewHolder, Date.ImageEntity item) {
        myViewHolder.setImageUrl(R.id.img_home_icon, item.getUrl());
        myViewHolder.setText(R.id.text_home_content, item.getName());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflateItemView(parent, R.layout.item_list_home));
    }

    public class MyViewHolder extends BaseRecyclerViewAdapter.SparseArrayViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
