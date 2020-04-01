package com.hritik.resume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Ach_Desc extends AppCompatActivity {

    EditText ed;
    Button achh;
    TextView tt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ach__desc);
        achh = findViewById(R.id.achdone_btn);
        ed=findViewById(R.id.ach_ed);
        Intent intn = getIntent();
        Bundle bd=intn.getExtras();
        final String table=bd.getString("from");
        final String no=bd.getString("num");
        tt=findViewById(R.id.txt1);
        if (table.equalsIgnoreCase("sk")){
            tt.setText("Enter Skill Details:");
        }
        else if(table.equalsIgnoreCase("ach"))
        {
            tt.setText("Enter Achievement Details:");
        }
        achh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed.getText().toString().length()>1){
                    register(table);
                    Intent ct=new Intent(getApplicationContext(),MainResume.class);
                    ct.putExtra("num",no);
                    startActivity(ct);
                }
            }
        });
    }
    private void register(String table){
        MyHelper dpHelper = new MyHelper(this);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        ContentValues insertValues = new ContentValues();
        insertValues.put(table+"_desc", ed.getText().toString());
        long rows =db.insert(table, null, insertValues);
        System.out.println(rows);
        //Permission is being asked
    }
}
