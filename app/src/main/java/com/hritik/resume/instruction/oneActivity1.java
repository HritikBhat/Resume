package com.hritik.resume.instruction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hritik.resume.R;

import static com.hritik.resume.instruction.InstructionMain.viewPager;


public class oneActivity1 extends Fragment {
    View view;
    Button right;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_one_activity1, container, false);
        right=view.findViewById(R.id.one_right);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
        return view;

    }
}
