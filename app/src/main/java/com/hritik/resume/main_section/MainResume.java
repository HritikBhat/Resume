package com.hritik.resume.main_section;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.hritik.resume.R;
import com.hritik.resume.TabAdapter;

public class MainResume extends AppCompatActivity{

    private TabAdapter adapter;
    private ViewPager viewPager;
    private Toolbar toolb;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_resume);
        viewPager = findViewById(R.id.viewPager);
        toolb=findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tbLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        setSupportActionBar(toolb);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new Personal(), "Personal");
        adapter.addFragment(new Objective(), "Objective");
        adapter.addFragment(new Experience(), "Experience");
        adapter.addFragment(new Internship(), "Internship");
        adapter.addFragment(new Education(), "Education");
        adapter.addFragment(new Project(), "Projects");
        adapter.addFragment(new Skills(), "Skills");
        adapter.addFragment(new Achievements(), "Achievements");
        adapter.addFragment(new Declaration(), "Declaration");
        viewPager.setAdapter(adapter);
        Intent intent = getIntent();

        if (intent.hasExtra("num")) {
            Bundle bd=intent.getExtras();
            int num= Integer.parseInt(bd.getString("num"));
            viewPager.setCurrentItem(num);
        }
        //
        tabLayout.setupWithViewPager(viewPager);
    }
}
