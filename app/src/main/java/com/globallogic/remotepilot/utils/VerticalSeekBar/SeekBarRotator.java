package com.globallogic.remotepilot.utils.VerticalSeekBar;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/*
 *  This ViewGroup contains a single view, which will be rotated by 90 degrees counterclockwise.
 */

public class SeekBarRotator extends FrameLayout {
    public SeekBarRotator(Context context) {
        super(context);
    }
    public SeekBarRotator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public SeekBarRotator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
// to delete
//    public SeekBarRotator(Context context, AttributeSet attrs, int defStyleAttr,
//                          int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final View child = getChildAt(0);
        if (child.getVisibility() != GONE) {
            // swap width and height for child
            measureChild(child, heightMeasureSpec, widthMeasureSpec);
            setMeasuredDimension(
                    child.getMeasuredHeightAndState(),
                    child.getMeasuredWidthAndState());
        } else {
            setMeasuredDimension(
                    resolveSizeAndState(0, widthMeasureSpec, 0),
                    resolveSizeAndState(0, heightMeasureSpec, 0));
        }
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final View child = getChildAt(0);
        if (child.getVisibility() != GONE) {
            // rotate the child 90 degrees counterclockwise around its upper-left
            child.setPivotX(0);
            child.setPivotY(0);
            child.setRotation(-90);
            // place the child below this view, so it rotates into view
            int mywidth = r - l;
            int myheight = b - t;
            int childwidth = myheight;
            int childheight = mywidth;
            child.layout(0, myheight, childwidth, myheight + childheight);
        }
    }
}