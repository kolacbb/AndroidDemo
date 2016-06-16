package io.github.kolacbb.androiddemo.AndroidHeros;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import io.github.kolacbb.androiddemo.R;

public class MeasureViewActivity extends AppCompatActivity {

    LinearLayout rootLayout;
    Button btChange0;
    Button btChange1;
    Button btChange2;
    CustomMeasureView customMeasureView;
    // 设置宽高400dp
    ViewGroup.LayoutParams params0 = new ViewGroup.LayoutParams(100, 100);
    // 设置宽高match_parent
    ViewGroup.LayoutParams params1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT);
    // 设置宽高warp_content
    ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_view);
        // 初始化View
        initView();
    }

    private void initView() {
        rootLayout = (LinearLayout) findViewById(R.id.container);
        btChange0 = (Button) findViewById(R.id.bt_change0);
        btChange1 = (Button) findViewById(R.id.bt_change1);
        btChange2 = (Button) findViewById(R.id.bt_change2);

        btChange0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customMeasureView != null) {
                    rootLayout.removeView(customMeasureView);
                }
                customMeasureView = new CustomMeasureView(MeasureViewActivity.this);
                customMeasureView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //设置View Layout_width， Layout_height
                customMeasureView.setLayoutParams(params0);

                rootLayout.addView(customMeasureView);
            }
        });
        btChange1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customMeasureView != null) {
                    rootLayout.removeView(customMeasureView);
                }
                customMeasureView = new CustomMeasureView(MeasureViewActivity.this);
                customMeasureView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //设置View Layout_width， Layout_height
                customMeasureView.setLayoutParams(params1);
                rootLayout.addView(customMeasureView);
            }
        });
        btChange2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customMeasureView != null) {
                    rootLayout.removeView(customMeasureView);
                }
                customMeasureView = new CustomMeasureView(MeasureViewActivity.this);
                customMeasureView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //设置View Layout_width， Layout_height
                customMeasureView.setLayoutParams(params2);
                rootLayout.addView(customMeasureView);
            }
        });

    }

    class CustomMeasureView extends View {

        public CustomMeasureView(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //把测量后的宽高值作为参数设置给setMeasureDimension() 方法
            setMeasuredDimension(
                    measureWidth(widthMeasureSpec),
                    measureHeight(heightMeasureSpec)
            );
        }


        //measureWidth 代码与measureHeight 代码基本一致。所以只以measureWidth 代码为例进行讲解
        private int measureWidth(int measureSpec) {
            int result = 0;
            // 从MeasureSpec对象中提取出具体的测量模式和大小
            int specMode = MeasureSpec.getMode(measureSpec);
            int specSize = MeasureSpec.getSize(measureSpec);

            // 通过判断测量的模式，给出不同的测量值
            if (specMode == MeasureSpec.EXACTLY) {
                // 精确值模式：match_parent或者指定大小
                // 当specMode 为EXACTLY时，直接使用指定的specSize即可
                result = specSize;
            } else {
                // 当specMode为At_MOST或UNSPECIFIED时，设置默认大小
                result = 50;
                if (specMode == MeasureSpec.AT_MOST) {
                    // AT_MOST，即相当于指定了wrap_content属性
                    // 控件的大小一般随着控件的子空间或者内容的变化而变化，此时控件的尺寸只要不超过父控件允许的最大尺寸即可
                    // 当specMode为AT_MOST时，需要取出我们指定的大小与specSie中最小的一个来作为最后的测量
                    result = Math.min(result, specSize);
                }
                // UNSPECIFIED：不指定其大小测量模式，View想多大就多大。
            }
            return result;
        }

        private int measureHeight(int measureSpec) {
            int result = 0;
            int specMode = MeasureSpec.getMode(measureSpec);
            int specSize = MeasureSpec.getSize(measureSpec);

            if (specMode == MeasureSpec.EXACTLY) {
                result = specSize;
            } else {
                result = 50;
                if (specMode == MeasureSpec.AT_MOST) {
                    result = Math.min(result, specSize);
                }
            }
            return result;
        }

    }
}
