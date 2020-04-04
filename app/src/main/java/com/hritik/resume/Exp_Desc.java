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
    String nam,cit,yr,dss,pts;
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

        Intent intt=getIntent();
        Bundle bd=intt.getExtras();
        if (bd!=null){
            nam=bd.getString("name");
            cit=bd.getString("city");
            yr=bd.getString("year");
            pts=bd.getString("post");
            dss=bd.getString("desc");
            String[] tofr=yr.split("-");
            //Toast.makeText(getApplicationContext(),"Update",Toast.LENGTH_LONG).show();
            name.setText(nam);
            city.setText(cit);
            from.setText(tofr[0].trim());
            post.setText(pts);
            to.setText(tofr[1].trim());
            desc.setText(dss);
            exp.setText("Update");
        }

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
        if(nam!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name.getText().toString());
            contentValues.put("city", city.getText().toString());
            contentValues.put("post", post.getText().toString());
            contentValues.put("tfrom", from.getText().toString());
            contentValues.put("tto",  to.getText().toString());
            contentValues.put("exp_desc", desc.getText().toString());
            String whereClause = "name =?";
            String whereArgs[] = {nam};
            db.update(table, contentValues, whereClause, whereArgs);
            db.close();
        }
        else{
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
}


