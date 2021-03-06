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
import com.hritik.resume.list_adapters.ListAdapterPRG;
import com.hritik.resume.MyHelper;
import com.hritik.resume.insertion.Prg_Desc;
import com.hritik.resume.R;

import java.util.ArrayList;

public class Project extends Fragment {
    ListView list;
    FloatingActionButton add;
    View view;
    ArrayList<String> desc,name,tech;
    ListAdapterPRG adapter;

    private void printListView(Activity act){
        desc.clear();
        MyHelper dpHelper = new MyHelper(act);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        try {
            //To get cursor
            Cursor cursor = dpHelper.getAllData("prg");
            while (cursor.moveToNext()) {
                //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
                name.add(cursor.getString(cursor.getColumnIndex("name")));
                tech.add(cursor.getString(cursor.getColumnIndex("tech")));
                desc.add(cursor.getString(cursor.getColumnIndex("prg_desc")));
            }
        }
        catch (Exception e){e.printStackTrace();}}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            try {
                name = new ArrayList<String>();
                tech = new ArrayList<String>();
                desc = new ArrayList<String>();
                printListView(getActivity());
                view = inflater.inflate(R.layout.fragment_project, container, false);
                adapter = new ListAdapterPRG(getActivity(), name, tech, desc);
                list = view.findViewById(R.id.prg_list);
                list.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
            MainResume mr = new MainResume();
            add = view.findViewById(R.id.prg_add_btn);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ct = new Intent(getActivity(), Prg_Desc.class);
                    startActivity(ct);
                }
            });
        }catch (Exception e){e.printStackTrace();}
        return view;
    }
}
