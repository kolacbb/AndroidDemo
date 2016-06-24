package io.github.kolacbb.androiddemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by Kola on 2016/6/24.
 */
public class DragView extends View {

    int lastX = 0;
    int lastY = 0;

    Scroller mScroller;

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        // 获取视图坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 处理输入的按下事件
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 处理输入的移动事件
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //method A
//                layout(getLeft() + offsetX,
//                        getTop() + offsetY,
//                        getRight() + offsetX,
//                        getBottom() + offsetY);

                // method B
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                // method C 移动View 的content， 若是View Group则移动其中的子view
                ((View) getParent()).scrollBy(-offsetX, -offsetY);

                // method D


                break;
            case MotionEvent.ACTION_UP:
                View viewGroup = (View) getParent();
                mScroller.startScroll(
                        viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY()
                );
                invalidate();
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 判断 Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(
                    mScroller.getCurrX(),
                    mScroller.getCurrY());
            invalidate();
        }
    }
}
