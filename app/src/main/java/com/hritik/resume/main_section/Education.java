package com.hritik.resume.main_section;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hritik.resume.insertion.Edu_Desc;
import com.hritik.resume.list_adapters.ListAdapterEDU;
import com.hritik.resume.MyHelper;
import com.hritik.resume.R;

import java.util.ArrayList;

public class Education extends Fragment {

    ListView list;
    FloatingActionButton add;
    View view;
    ArrayList<String> name,city,deg,year,type1,ptcg;
    ListAdapterEDU adapter;

    private void printListView(Activity act){
        MyHelper dpHelper = new MyHelper(act);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        try {
            //To get cursor
            Cursor cursor = dpHelper.getAllData("edu");
            while (cursor.moveToNext()) {
                //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
                name.add(cursor.getString(cursor.getColumnIndex("name")));
                city.add(cursor.getString(cursor.getColumnIndex("city")));
                deg.add(cursor.getString(cursor.getColumnIndex("degree")));
                year.add(cursor.getString(cursor.getColumnIndex("tfrom"))+" - "+cursor.getString(cursor.getColumnIndex("tto")));
                type1.add(cursor.getString(cursor.getColumnIndex("type")));
                ptcg.add(cursor.getString(cursor.getColumnIndex("grade")));


            }
        }
        catch (Exception e){e.printStackTrace();}}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            try {
                name = new ArrayList<String>();
                city = new ArrayList<String>();
                deg = new ArrayList<String>();
                year = new ArrayList<String>();
                type1 = new ArrayList<String>();
                ptcg = new ArrayList<String>();
                printListView(getActivity());
                view = inflater.inflate(R.layout.fragment_education, container, false);
                adapter = new ListAdapterEDU(getActivity(), name, city, deg, year, ptcg, type1);
                list = view.findViewById(R.id.edu_list);
                list.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
            MainResume mr = new MainResume();
            add = view.findViewById(R.id.edu_add_btn);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ct = new Intent(getActivity(), Edu_Desc.class);
                    startActivity(ct);
                }
            });
        }catch (Exception e){e.printStackTrace();}
        return view;

    }
}
