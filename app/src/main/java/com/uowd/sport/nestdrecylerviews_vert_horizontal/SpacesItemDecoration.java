package com.uowd.sport.nestdrecylerviews_vert_horizontal;

/**
 * Created by Developer on 1/21/2016.
 */

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = 4;
        outRect.right = 4;
        outRect.top=0;
        outRect.bottom = space;
    }
}