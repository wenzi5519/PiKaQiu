package wzw.pikaqiu.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import wzw.pikaqiu.R;
import wzw.pikaqiu.model.Item;
import wzw.pikaqiu.ui.base.BaseRecyclerViewAdapter;


public class WidgetListAdapter extends BaseRecyclerViewAdapter<Item, WidgetListAdapter.MyViewHolder> {
    /**
     * @param list the datas to attach the adapter
     */
    public WidgetListAdapter(List list) {
        super(list);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        return new MyViewHolder(inflateItemView(viewGroup, R.layout.item_list_home));
//            return new MyViewHolder(inflateItemView(viewGroup, R.layout.item_grid_recgrid));
    }

    @Override
    protected void bindDataToItemView(MyViewHolder myViewHolder, Item item) {
        myViewHolder.setImageUrl(R.id.img_home_icon, item.imageUrl);
        myViewHolder.setText(R.id.text_home_content, item.description);
    }

    @Override
    public void setList(List<Item> list) {
        super.setList(list);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends BaseRecyclerViewAdapter.SparseArrayViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
