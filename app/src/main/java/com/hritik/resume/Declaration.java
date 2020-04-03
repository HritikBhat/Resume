package com.hritik.resume;

import android.app.Activity;
import android.content.ContentValues;
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
import android.widget.EditText;
import android.widget.Toast;

public class Declaration extends Fragment {
    View view;
    EditText ed;
    Button print,done;

    private String printListView(Activity act) {
        try {
            MyHelper dpHelper = new MyHelper(act);
            SQLiteDatabase db = dpHelper.getReadableDatabase();
            //To get cursor
            Cursor cursor = dpHelper.getAllData("dec");
            cursor.moveToFirst();
            //System.out.println("Rows: "+cursor.getCount());
            if (cursor.getCount() == 1) {
                //System.out.println(cursor.getCount());
                return cursor.getString(cursor.getColumnIndex("dec_desc"));
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
        db.execSQL("DELETE from dec");
        ContentValues insertValues = new ContentValues();
        insertValues.put("dec_desc", ed.getText().toString());
        long rows =db.insert("dec", null, insertValues);
        Toast.makeText(act,"Declaration is saved!",Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_declaration, container, false);
        ed=view.findViewById(R.id.ed_dec);
        ed.setText(printListView(getActivity()));
        done=view.findViewById(R.id.dn_btn);
        print=view.findViewById(R.id.print1);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDec(getActivity());
            }
        });
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ct = new Intent(getActivity(), Selection.class);
                startActivity(ct);
            }
        });
        return view;
    }
}
