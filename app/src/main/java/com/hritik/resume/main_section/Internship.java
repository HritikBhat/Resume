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
import com.hritik.resume.insertion.Int_Desc;
import com.hritik.resume.list_adapters.ListAdapterINT;
import com.hritik.resume.MyHelper;
import com.hritik.resume.R;

import java.util.ArrayList;

public class Internship extends Fragment {
    ListView list;
    FloatingActionButton add;
    View view;
    ArrayList<String> name,city,year,descp,post;
    ListAdapterINT adapter;

    private void printListView(Activity act){
        MyHelper dpHelper = new MyHelper(act);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        try {
            //To get cursor
            Cursor cursor = dpHelper.getAllData("intr");
            while (cursor.moveToNext()) {
                //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
                name.add(cursor.getString(cursor.getColumnIndex("name")));
                city.add(cursor.getString(cursor.getColumnIndex("city")));
                year.add(cursor.getString(cursor.getColumnIndex("tfrom"))+" - "+cursor.getString(cursor.getColumnIndex("tto")));
                descp.add(cursor.getString(cursor.getColumnIndex("intr_desc")));

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
                descp = new ArrayList<String>();
                year = new ArrayList<String>();
                printListView(getActivity());
                view = inflater.inflate(R.layout.fragment_internship, container, false);
                adapter = new ListAdapterINT(getActivity(), name, city, year, descp);
                list = view.findViewById(R.id.int_list);
                list.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
            MainResume mr = new MainResume();
            add = view.findViewById(R.id.int_add_btn);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ct = new Intent(getActivity(), Int_Desc.class);
                    startActivity(ct);
                }
            });
        }catch (Exception e){e.printStackTrace();}
        return view;

    }
}
