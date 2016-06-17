package io.github.kolacbb.androiddemo.AndroidHeros.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.kolacbb.androiddemo.R;

public class CustomViewExpandActivity extends AppCompatActivity {

    LinearLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_expand);
        rootView = (LinearLayout) findViewById(R.id.root_view);
        CustomTextView customTextView = new CustomTextView(this);
        customTextView.setText("Hello Custom View, 蓝色的框和黄色的矩形，都在文字背后");
        rootView.addView(customTextView);

        CustomTextView1 customTextView1 = new CustomTextView1(this);
        customTextView1.setText("Hello this is a text Demo, 通过设置一个不断变化的LinearGradient， 并使用带有属性的Paint绘制要显示的文字， 在onSizeChanged（）方法中进行对象初始化工作，并根据View的宽带设置一个LinearGradient渐变渲染器");
        rootView.addView(customTextView1);

    }

    private class CustomTextView extends TextView {

        public CustomTextView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // 初始化两个画笔
            Paint paint1 = new Paint();
            paint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
            paint1.setStyle(Paint.Style.STROKE);

            Paint paint2 = new Paint();
            paint2.setColor(Color.YELLOW);
            paint2.setStyle(Paint.Style.FILL);


            // 在回调父类方法前，实现自己的逻辑，对TextView来说即是在绘制文本内容前
            canvas.drawRect(
                    0,
                    0,
                    getMeasuredWidth(),
                    getMeasuredHeight(),
                    paint1
            );
            //super.onDraw(canvas);
            // 在回调父类放法后，实现自己的逻辑，对TextView来说即是在绘制文本内容后
            canvas.drawRect(
                    5,
                    5,
                    getMeasuredWidth() - 5,
                    getMeasuredHeight() - 5,
                    paint2
            );
            //外层黄色，内层蓝色
            canvas.save();
            // 绘制文字前，平移10像素
            canvas.translate(5, 0);
            //父类完成的方法，即绘制文本
            super.onDraw(canvas);
            canvas.restore();
        }
    }

    private class CustomTextView1 extends TextView {

        int mViewWidth = 0;
        LinearGradient mLinearGradient;
        Matrix mGradientMatrix;
        Paint mPaint;

        int mTranslate = 0;

        public CustomTextView1(Context context) {
            super(context);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            if (mViewWidth == 0) {
                mViewWidth = getMeasuredWidth();
                if (mViewWidth > 0) {
                    mPaint = getPaint();
                    mLinearGradient = new LinearGradient(
                            0,
                            0,
                            mViewWidth,
                            0,
                            new int[]{
                                    Color.BLUE, 0xffffffff,
                                    Color.BLUE
                            },
                            null,
                            Shader.TileMode.CLAMP);
                    mPaint.setShader(mLinearGradient);
                    mGradientMatrix = new Matrix();
                }
            }
        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (mGradientMatrix != null) {
                mTranslate += mViewWidth/5;
                if (mTranslate > 2 * mViewWidth) {
                    mTranslate = -mViewWidth;
                }
                mGradientMatrix.setTranslate(mTranslate, 0);
                mLinearGradient.setLocalMatrix(mGradientMatrix);
                postInvalidateDelayed(100);
            }
        }
    }
}
