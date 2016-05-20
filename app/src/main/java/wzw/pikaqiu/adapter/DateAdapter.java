package wzw.pikaqiu.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wzw.pikaqiu.R;
import wzw.pikaqiu.model.Date;
import wzw.pikaqiu.ui.base.BaseRecyclerViewAdapter;

/**
 * Created by Administrator on 2016/5/20.
 */
public class DateAdapter extends BaseRecyclerViewAdapter<Date,DateAdapter.MyViewHolder> {
    /**
     * @param list the datas to attach the adapter
     */
    public DateAdapter(List<Date> list) {
        super(list);
    }

    @Override
    protected void bindDataToItemView(MyViewHolder myViewHolder, Date item) {
        myViewHolder.setText(R.id.text_group,item.getTitle());
        List<Date.ImageEntity> list = item.getList();

        DateItemAdapter dateItemAdapter=new DateItemAdapter(list);
        RecyclerView view= myViewHolder.getView(R.id.rec_grid);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(myViewHolder.itemView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        view.setLayoutManager(linearLayoutManager);
        view.setAdapter(dateItemAdapter);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflateItemView(parent, R.layout.item_list_reclist));
    }

    public class MyViewHolder extends BaseRecyclerViewAdapter.SparseArrayViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
