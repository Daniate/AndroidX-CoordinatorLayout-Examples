package com.daniate.androidx_coordinatorlayout_examples;

import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;

public class ScrollPinTopActivity extends AppCompatActivity {

    AppBarLayout app_bar_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_pin_top);

        app_bar_layout = findViewById(R.id.app_bar_layout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            app_bar_layout.setOutlineProvider(null);
        }
    }
}
