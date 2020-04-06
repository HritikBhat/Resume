package com.hritik.resume;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
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

import java.util.regex.Pattern;

public class Personal extends Fragment {
    View view;
    EditText name,addr,phn,dobe,eml;
    String nm,ph,ad,db,em;
    Button done;

    private Boolean isValid(){

        if (Pattern.matches("^[0-9]{2}[.][0-9]{2}[.][0-9]{4}$",dobe.getText().toString())!=true){
            Toast.makeText(getActivity(),"Date should be in DD.MM.YYYY format.",Toast.LENGTH_LONG).show();
            return false;}
        if (Pattern.matches("^[0-9A-Za-z][0-9A-Za-z'.\"+$%_#*]{1,62}@[0-9A-Za-z.]{1,30}[.][A-Za-z]{1,5}$",eml.getText().toString())!=true){
            Toast.makeText(getActivity(),"Email is not valid.",Toast.LENGTH_LONG).show();
            return false;
        }

        if (Pattern.matches("^[A-Z]{1}[A-Za-z]+\\s[A-Za-z]+$",name.getText().toString())!=true){
            Toast.makeText(getActivity(),"Enter full name properly.",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
    private Cursor printListView(Activity act) {
        try {
            MyHelper dpHelper = new MyHelper(act);
            SQLiteDatabase db = dpHelper.getReadableDatabase();
            //To get cursor
            Cursor cursor = dpHelper.getAllData("psl");
            cursor.moveToFirst();
            //System.out.println("Rows: "+cursor.getCount());
            if (cursor.getCount() == 1) {
                //System.out.println(cursor.getCount());
                return cursor;
            } else {
                //Toast.makeText(act,"Blank",Toast.LENGTH_LONG);
                return null;
            }
        }
        catch(Exception e){e.printStackTrace();}
        return null;
    }
    private void updatePsl(Activity act) {
        MyHelper dpHelper = new MyHelper(act);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        if(isValid()) {
            //To get cursor
            db.execSQL("DELETE from psl");
            ContentValues insertValues = new ContentValues();
            insertValues.put("name", name.getText().toString());
            insertValues.put("addr", addr.getText().toString());
            insertValues.put("phone", phn.getText().toString());
            insertValues.put("email", eml.getText().toString());
            insertValues.put("dob", dobe.getText().toString());
            long rows = db.insert("psl", null, insertValues);
            Toast.makeText(act, "Saved!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_personal, container, false);
        Cursor cursor=printListView(getActivity());
        if (cursor!=null){
            nm=cursor.getString(cursor.getColumnIndex("name"));
            ad=cursor.getString(cursor.getColumnIndex("addr"));
            ph=cursor.getString(cursor.getColumnIndex("phone"));
            em=cursor.getString(cursor.getColumnIndex("email"));
            db=cursor.getString(cursor.getColumnIndex("dob"));
        }
        else{
            nm="";
            ad="";
            ph="";
            db="";
        }

        name=view.findViewById(R.id.psl_name);
        name.setText(nm);
        addr=view.findViewById(R.id.psl_addr);
        addr.setText(ad);
        phn=view.findViewById(R.id.psl_phone);
        phn.setText(ph);
        eml=view.findViewById(R.id.psl_email);
        eml.setText(em);
        dobe=view.findViewById(R.id.psl_dob);
        dobe.setText(db);
        done=view.findViewById(R.id.psldone_btn);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePsl(getActivity());
            }
        });
        return view;
    }
}

