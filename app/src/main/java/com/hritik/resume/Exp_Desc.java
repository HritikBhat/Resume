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

import java.util.ArrayList;
import java.util.Arrays;

public class Exp_Desc extends AppCompatActivity {
    EditText name,city,post,from,to,desc;
    Button exp;

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
        setContentView(R.layout.activity_exp__desc);
        exp = findViewById(R.id.expdone_btn);
        name=findViewById(R.id.exp_name);
        city=findViewById(R.id.exp_city);
        post=findViewById(R.id.exp_post);
        from=findViewById(R.id.exp_from);
        to=findViewById(R.id.exp_to);
        desc=findViewById(R.id.exp_desc);

        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getLengthStatus()){
                    register("exp");
                    Intent ct=new Intent(getApplicationContext(),MainResume.class);
                    ct.putExtra("num","2");
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
        insertValues.put("city", city.getText().toString());
        insertValues.put("post", post.getText().toString());
        insertValues.put("tfrom", from.getText().toString());
        insertValues.put("tto",  to.getText().toString());
        insertValues.put("exp_desc", desc.getText().toString());
        long rows =db.insert(table, null, insertValues);
        System.out.println(rows);
        //Permission is being asked
    }
}


