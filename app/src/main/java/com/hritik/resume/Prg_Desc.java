package com.hritik.resume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Prg_Desc extends AppCompatActivity {

    EditText name,tech,desc;
    Button prg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prg__desc);
        prg = findViewById(R.id.prgdone_btn);
        name=findViewById(R.id.prg_name);
        tech=findViewById(R.id.prg_tech);
        desc=findViewById(R.id.prg_desc);

        prg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((name.getText().toString().length()>1)&&(tech.getText().toString().length()>1)&&(desc.getText().toString().length()>1)){
                    register("prg");
                    Intent ct=new Intent(getApplicationContext(),MainResume.class);
                    ct.putExtra("num","5");
                    startActivity(ct);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Details are not filled",Toast.LENGTH_LONG).show();}
            }
        });
    }
    private void register(String table){
        MyHelper dpHelper = new MyHelper(this);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        ContentValues insertValues = new ContentValues();
        insertValues.put("name", name.getText().toString());
        insertValues.put("tech", tech.getText().toString());
        insertValues.put("prg_desc", desc.getText().toString());
        long rows =db.insert(table, null, insertValues);
        System.out.println(rows);
        //Permission is being asked
    }
}
