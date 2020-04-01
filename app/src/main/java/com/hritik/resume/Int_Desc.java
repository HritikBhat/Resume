package com.hritik.resume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

public class Int_Desc extends AppCompatActivity {
    EditText name,city,from,to,desc;
    Button intr;

    protected boolean getLengthStatus(){
        ArrayList<EditText> arr =new ArrayList<EditText>(Arrays.asList(name,city,from,to,desc));
        for(int i=0;i<arr.size();i++) {
            if (arr.get(i).getText().length() < 1) {
                return false;
            }
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int__desc);
        intr = findViewById(R.id.intdone_btn);
        name=findViewById(R.id.int_name);
        city=findViewById(R.id.int_city);
        from=findViewById(R.id.int_from);
        to=findViewById(R.id.int_to);
        desc=findViewById(R.id.int_desc);

        intr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getLengthStatus()){
                    register("intr");
                    Intent ct=new Intent(getApplicationContext(),MainResume.class);
                    ct.putExtra("num","3");
                    startActivity(ct);
                }
            }
        });
    }
    private void register(String table){
        MyHelper dpHelper = new MyHelper(this);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        ContentValues insertValues = new ContentValues();
        insertValues.put("name", name.getText().toString());
        insertValues.put("city", city.getText().toString());
        insertValues.put("tfrom", from.getText().toString());
        insertValues.put("tto",  to.getText().toString());
        insertValues.put("intr_desc", desc.getText().toString());
        long rows =db.insert(table, null, insertValues);
        System.out.println(rows);
        //Permission is being asked
    }
}

