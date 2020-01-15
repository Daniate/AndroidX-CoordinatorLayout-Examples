package com.daniate.androidx_coordinatorlayout_examples;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.chenglei1986.statusbar.StatusBarColorManager;
import com.google.android.material.appbar.AppBarLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

public class ScrollScaleActivity extends AppCompatActivity {

    ImageView iv_scalable;
    Integer iv_scalable_width;
    Integer iv_scalable_height;

    AppBarLayout app_bar_layout_title;
    TextView tv_title;

    SmartRefreshLayout smart_refresh_layout;

    AppBarLayout app_bar_layout_info;

    StatusBarColorManager status_bar_color_manager;

    public int getStatusBarHeight() {
        int h = 0;
        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            h = getResources().getDimensionPixelSize(resId);
        }
        return h;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_scale);

        status_bar_color_manager = new StatusBarColorManager(this);

        iv_scalable = findViewById(R.id.iv_scalable);
        app_bar_layout_title = findViewById(R.id.app_bar_layout_title);
        tv_title = findViewById(R.id.tv_title);
        smart_refresh_layout = findViewById(R.id.smart_refresh_layout);
        app_bar_layout_info = findViewById(R.id.app_bar_layout_info);

        app_bar_layout_title.setPadding(0, getStatusBarHeight(), 0, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            app_bar_layout_title.setOutlineProvider(null);
            app_bar_layout_info.setOutlineProvider(null);
        }

        smart_refresh_layout.setRefreshHeader(new ClassicsHeader(this));
        smart_refresh_layout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                super.onHeaderMoving(header, isDragging, percent, offset, headerHeight, maxDragHeight);
                if (null == iv_scalable_width) {
                    iv_scalable_width = iv_scalable.getWidth();
                }
                if (null == iv_scalable_height) {
                    iv_scalable_height = iv_scalable.getHeight();
                }
                ViewGroup.LayoutParams layoutParams = iv_scalable.getLayoutParams();
                layoutParams.width = (offset << 3) + iv_scalable_width;
                layoutParams.height = offset + iv_scalable_height;
                iv_scalable.setLayoutParams(layoutParams);
            }
        });
        smart_refresh_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smart_refresh_layout.finishRefresh(1000);
            }
        });

        app_bar_layout_info.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                int range = appBarLayout.getTotalScrollRange();
                int alpha = (int) ((float) Math.abs(i) / range * 255);
                int color = Color.parseColor(String.format("#%02XFFFFFF", alpha));
                app_bar_layout_title.setBackgroundColor(color);

                if (alpha > 200) {
                    status_bar_color_manager.setLightStatusBar(true);
                    tv_title.setTextColor(Color.BLACK);
                } else {
                    status_bar_color_manager.setLightStatusBar(false);
                    tv_title.setTextColor(Color.WHITE);
                }
            }
        });
    }
}
