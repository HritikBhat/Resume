package com.hritik.resume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class InstructionMain extends AppCompatActivity {
    private TabAdapter adapter;
    public static ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_main);

        viewPager = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tbLayout2);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new oneActivity1(), "");
        adapter.addFragment(new oneActivity2(), "");
        adapter.addFragment(new oneActivity3(), "");
        adapter.addFragment(new oneActivity4(), "");
        adapter.addFragment(new oneActivity5(), "");
        adapter.addFragment(new oneActivityF(), "");
        viewPager.setAdapter(adapter);
        Intent intent = getIntent();

        if (intent.hasExtra("num")) {
            Bundle bd=intent.getExtras();
            int num= Integer.parseInt(bd.getString("num"));
            viewPager.setCurrentItem(num);
        }
    }
}
