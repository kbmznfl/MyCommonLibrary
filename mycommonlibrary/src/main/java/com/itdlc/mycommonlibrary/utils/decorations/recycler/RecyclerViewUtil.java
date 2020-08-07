package com.itdlc.mycommonlibrary.utils.decorations.recycler;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itdlc.mycommonlibrary.utils.decorations.linear.HorizontalSpace;
import com.itdlc.mycommonlibrary.utils.decorations.linear.VerticalSpace;

public class RecyclerViewUtil {

    /**
     * 添加item间的间距
     *
     * @param recyclerView
     * @param layoutManager
     * @param space 间距大小，像素
     */
    public static void addSpace(RecyclerView recyclerView, LinearLayoutManager layoutManager,
                                int space) {

        RecyclerView.ItemDecoration decoration;
        if (layoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
            decoration = new HorizontalSpace(space);
        } else {
            decoration = new VerticalSpace(space);
        }
        recyclerView.addItemDecoration(decoration);
    }

    /**
     * 添加item间的间距
     *
     * @param recyclerView
     * @param layoutManager GridLayoutManager
     * @param horizontalSpace 水平间距，像素
     * @param verticalSpace 垂直间距，像素
     */
    public static void addSpace(RecyclerView recyclerView, GridLayoutManager layoutManager,
                                int horizontalSpace, int verticalSpace) {
        int spanCount = layoutManager.getSpanCount();
        SpacesItemDecoration itemDecoration =
                SpacesItemDecoration.newInstance(horizontalSpace, verticalSpace, spanCount);
        recyclerView.addItemDecoration(itemDecoration);
    }
}
