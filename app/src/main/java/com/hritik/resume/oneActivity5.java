package com.hritik.resume;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.hritik.resume.InstructionMain.viewPager;


public class oneActivity5 extends Fragment {
    View view;
    Button left,right;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_one_activity5, container, false);
        left=view.findViewById(R.id.five_left);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(3);
            }
        });
        right=view.findViewById(R.id.five_right);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(5);

            }
        });
        return view;

    }
}
