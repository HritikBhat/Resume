package com.hritik.resume.instruction;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hritik.resume.R;

import static com.hritik.resume.instruction.InstructionMain.viewPager;

public class oneActivity3_1 extends Fragment {

    View view;
    Button left,right;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_one_activity3_1, container, false);
        left=view.findViewById(R.id.threeone_left);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });
        right=view.findViewById(R.id.threeone_right);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(4);
            }
        });
        return view;

    }
}
