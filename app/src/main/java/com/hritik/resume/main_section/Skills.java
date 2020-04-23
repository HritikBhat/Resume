package com.hritik.resume.main_section;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hritik.resume.insertion.Ach_Desc;
import com.hritik.resume.MyHelper;
import com.hritik.resume.list_adapters.MyListAdapter;
import com.hritik.resume.R;

import java.util.ArrayList;

public class Skills extends Fragment {
    ListView list;
    FloatingActionButton add;
    View view;
    ViewPager pv;
    ArrayList<String> desc;
    MyListAdapter adapter;

    private void printListView(Activity act){
        desc.clear();
        MyHelper dpHelper = new MyHelper(act);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        try {
            //To get cursor
            Cursor cursor = dpHelper.getAllData("sk");
            while (cursor.moveToNext()) {
                //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
                desc.add(cursor.getString(cursor.getColumnIndex("sk_desc")));
            }
        }
        catch (Exception e){e.printStackTrace();}}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            try {
                desc = new ArrayList<String>();
                printListView(getActivity());
                view = inflater.inflate(R.layout.fragment_skills, container, false);
                adapter = new MyListAdapter(getActivity(), desc, "sk");
                list = view.findViewById(R.id.sk_list);
                list.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
            MainResume mr = new MainResume();
            add = view.findViewById(R.id.sk_add_btn);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ct = new Intent(getActivity(), Ach_Desc.class);
                    ct.putExtra("from", "sk");
                    ct.putExtra("num", "6");
                    startActivity(ct);
                }
            });
        }catch (Exception e){e.printStackTrace();}
        return view;
    }
}
