package com.hritik.resume;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class Achievements extends Fragment {

    ListView list;
    Button add,reset;
    View view;
    ArrayList<String> desc;
    MyListAdapter adapter;

    private void printListView(Activity act){
            desc.clear();
            MyHelper dpHelper = new MyHelper(act);
            SQLiteDatabase db = dpHelper.getReadableDatabase();
            try {
                //To get cursor
                Cursor cursor = dpHelper.getAllData("ach");
                while (cursor.moveToNext()) {
                    //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
                    desc.add(cursor.getString(cursor.getColumnIndex("ach_desc")));
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
                view = inflater.inflate(R.layout.fragment_achievements, container, false);
                adapter = new MyListAdapter(getActivity(), desc, "ach");
                list = view.findViewById(R.id.list);
                list.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
            MainResume mr = new MainResume();
            add = view.findViewById(R.id.add_btn);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ct = new Intent(getActivity(), Ach_Desc.class);
                    ct.putExtra("from", "ach");
                    ct.putExtra("num", "7");
                    startActivity(ct);
                }
            });
            reset = view.findViewById(R.id.ach_reset);
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyHelper hp = new MyHelper(getActivity());
                    hp.onDeleteAll("ach");
                    desc.clear();
                    adapter.notifyDataSetChanged();
                }
            });
        }catch (Exception e){e.printStackTrace();}
        return view;

    }
}
