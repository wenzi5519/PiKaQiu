package wzw.pikaqiu.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 子文 on 2015/12/11.
 *  BaseAdapter 的基类
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> list;
    private int resId;

    /**
     * Constructor
     *
     * @param context 用来创建布局填充器的上下文
     * @param list    存储数据的集合
     * @param resId   item 布局文件的 id
     */
    public MyBaseAdapter(Context context, List<T> list, int resId) {
        super();
        this.context = context;
        this.list = list;
        this.resId = resId;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 我们可以把 view 放到另外一个类中返回
        MyHolder myHolder = MyHolder.getHolder(context, convertView, resId);

        fillData(myHolder, list.get(position));
        return myHolder.getmConvertView();
    }

    /**
     * 填充数据
     *
     * @param holder 通过 myHolder对象使用 MyHolder类中的 findView 方法查找控件
     * @param t      list集合中存储的实体对象
     */

    public abstract void fillData(MyHolder holder, T t);

    /**
     * 定义 MyHolder类，获得 view
     */
    public static class MyHolder {
        private View mConvertView;// 返回给 listview 的 view

        /**
         * 通过布局填充器加载布局生成 view ，并给 view 设置当前 viewholder 为 tag
         *
         * @param context
         * @param resId
         */
        public MyHolder(Context context, int resId) {

            mConvertView = LayoutInflater.from(context).inflate(resId, null);
            mConvertView.setTag(this);
        }

        public View getmConvertView() {
            return mConvertView;
        }

        /**
         * @param context
         * @param convertView listview 的覆用 view,根据它的值是否为 null 来判断是否生成新的 view
         * @param resId
         * @return
         */
        public static MyHolder getHolder(Context context, View convertView,
                                         int resId) {
            MyHolder myHolder = null;
            if (convertView == null) {
                // 如果为 null代表没有可以复用的view, 我在构造方法中创建新的
                myHolder = new MyHolder(context, resId);
            } else {
                // 因为创建的时候顺便给了 tag,所以可以根据 tag 再重新获取到 viewholder
                myHolder = (MyHolder) convertView.getTag();
            }
            return myHolder;
        }

        /**
         * findViewById 方法
         *
         * @param id listview中每一项item中的控件id
         * @return
         */
        public View findView(int id) {
            return mConvertView.findViewById(id);
        }

        /**
         * 为Textview 设置文本
         *
         * @param resId
         * @param text
         * @return
         */
        public MyHolder setText(int resId, String text) {
            TextView tv = (TextView) findView(resId);
            tv.setText(text);
            return this;
        }
    }
}
