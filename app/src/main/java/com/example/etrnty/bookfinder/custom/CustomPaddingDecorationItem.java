package com.example.etrnty.bookfinder.custom;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.etrnty.bookfinder.R;

public class CustomPaddingDecorationItem extends RecyclerView.ItemDecoration {

    private int mPaddingPx;
    private int mPaddingEdgesPx;
    private int mPaddingLeft;
    private int mPaddingRight;

    public CustomPaddingDecorationItem(Activity activity) {
        final Resources resources = activity.getResources();
        mPaddingPx = (int) resources.getDimension(R.dimen.paddingItemDecorationDefault);
        mPaddingEdgesPx = (int) resources.getDimension(R.dimen.paddingItemDecorationEdge);
        //mPaddingLeft = (int) resources.getDimension(R.dimen.paddingLeft);
        //mPaddingRight = (int) resources.getDimension(R.dimen.paddingRight);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        final int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }
        int orientation = getOrientation(parent);
        final int itemCount = state.getItemCount();

        //int left = mPaddingLeft;
        int left = 0;
        int top = 0;
        //int right = mPaddingRight;
        int right = 0;
        int bottom = 0;

        /** HORIZONTAL */
        if (orientation == LinearLayoutManager.HORIZONTAL) {
            /** all positions */
            left = mPaddingPx;
            right = mPaddingPx;

            /** first position */
            if (itemPosition == 0) {
                left += mPaddingEdgesPx;
            }
            /** last position */
            else if (itemCount > 0 && itemPosition == itemCount - 1) {
                right += mPaddingEdgesPx;
            }
        }
        /** VERTICAL */
        else {
            /** all positions */
            top = mPaddingPx;
            bottom = mPaddingPx;

            /** first position */
            if (itemPosition == 0) {
                top += mPaddingEdgesPx;
            }
            /** last position */
            else if (itemCount > 0 && itemPosition == itemCount - 1) {
                bottom += mPaddingEdgesPx;
            }
        }

        if (!isReverseLayout(parent)) {
            outRect.set(left, top, right, bottom);
        } else {
            outRect.set(right, bottom, left, top);
        }
    }

    private boolean isReverseLayout(RecyclerView parent) {
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            return layoutManager.getReverseLayout();
        } else {
            throw new IllegalStateException("CustomPaddingItemDecoration can only be used with a LinearLayoutManager.");
        }
    }

    private int getOrientation(RecyclerView parent) {
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            return layoutManager.getOrientation();
        } else {
            throw new IllegalStateException("CustomPaddingItemDecoration can only be used with a LinearLayoutManager.");
        }
    }
}