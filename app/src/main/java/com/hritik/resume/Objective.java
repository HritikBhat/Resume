package com.hritik.resume;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Objective extends Fragment {

    View view;
    Button sv;
    EditText ed;

    private String printListView(Activity act) {
        try {
            MyHelper dpHelper = new MyHelper(act);
            SQLiteDatabase db = dpHelper.getReadableDatabase();
            //To get cursor
            Cursor cursor = dpHelper.getAllData("obj");
            cursor.moveToFirst();
            //System.out.println("Rows: "+cursor.getCount());
            if (cursor.getCount() == 1) {
                //System.out.println(cursor.getCount());
                return cursor.getString(cursor.getColumnIndex("obj_desc"));
            } else {
                //Toast.makeText(act,"Blank",Toast.LENGTH_LONG);
                return "";
            }
        }
        catch(Exception e){e.printStackTrace();}
        return "Error";
    }
    private void updateDec(Activity act) {
        MyHelper dpHelper = new MyHelper(act);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        //To get cursor
        db.execSQL("DELETE from obj");
        ContentValues insertValues = new ContentValues();
        insertValues.put("obj_desc", ed.getText().toString());
        long rows =db.insert("obj", null, insertValues);
        Toast.makeText(act,"Objective is saved!",Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_objective, container, false);
        ed=view.findViewById(R.id.ed_obj);
        ed.setText(printListView(getActivity()));
        sv=view.findViewById(R.id.obj_sv_btn);
        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDec(getActivity());
            }
        });

        return view;
    }

}
