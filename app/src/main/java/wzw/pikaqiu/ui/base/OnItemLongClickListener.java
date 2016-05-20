package wzw.pikaqiu.ui.base;

import android.view.View;

public interface OnItemLongClickListener<T> {
    void onLongClick(View view, T item);
}