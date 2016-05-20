package wzw.pikaqiu.ui.base;

import android.view.View;

public interface OnItemClickListener<T> {
    void onClick(View view, T item);
}