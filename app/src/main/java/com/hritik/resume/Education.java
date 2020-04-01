package com.hritik.resume;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Education extends Fragment {

    ListView list;
    Button add,reset;
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
            name = new ArrayList<String>();
            city = new ArrayList<String>();
            deg= new ArrayList<String>();
            year = new ArrayList<String>();
            type1 = new ArrayList<String>();
            ptcg = new ArrayList<String>();
            printListView(getActivity());
            view = inflater.inflate(R.layout.fragment_education, container, false);
            adapter = new ListAdapterEDU(getActivity(),name,city,deg,year,ptcg);
            list = (ListView) view.findViewById(R.id.edu_list);
            list.setAdapter(adapter);
        }
        catch (Exception e){e.printStackTrace();}
        MainResume mr=new MainResume();
        add = view.findViewById(R.id.edu_add_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ct = new Intent(getActivity(), Edu_Desc.class);
                startActivity(ct);
            }
        });
        reset = view.findViewById(R.id.edu_reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyHelper hp = new MyHelper(getActivity());
                hp.onDeleteAll("edu");
                name.clear();
                city.clear();
                deg.clear();
                year.clear();
                type1.clear();
                ptcg.clear();
                adapter.notifyDataSetChanged();
            }
        });
        return view;

    }
}
