package com.hritik.resume.insertion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hritik.resume.MyHelper;
import com.hritik.resume.R;
import com.hritik.resume.main_section.MainResume;

public class Prg_Desc extends AppCompatActivity {

    EditText name,tech,desc;
    Button prg;
    String nam,tch,des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prg__desc);
        prg = findViewById(R.id.prgdone_btn);
        name=findViewById(R.id.prg_name);
        tech=findViewById(R.id.prg_tech);
        desc=findViewById(R.id.prg_desc);

        Intent intt=getIntent();
        Bundle bd=intt.getExtras();
        if (bd!=null){
        nam=bd.getString("name");
        tch=bd.getString("tech");
        des=bd.getString("desc");
        if(des!=null && tch!=null && nam!=null){
            //Toast.makeText(getApplicationContext(),"Update",Toast.LENGTH_LONG).show();
            name.setText(nam);
            tech.setText(tch);
            desc.setText(des);
            prg.setText("Update");
        }}
        prg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((name.getText().toString().length()>1)&&(tech.getText().toString().length()>1)&&(desc.getText().toString().length()>1)){
                    register("prg");
                    Intent ct=new Intent(getApplicationContext(), MainResume.class);
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
        if(des!=null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name.getText().toString());
            contentValues.put("tech", tech.getText().toString());
            contentValues.put("prg_desc", desc.getText().toString());
            String whereClause = table + "_desc =?";
            String whereArgs[] = {des};
            db.update(table, contentValues, whereClause, whereArgs);
            db.close();
        }
        else{
        ContentValues insertValues = new ContentValues();
        insertValues.put("name", name.getText().toString());
        insertValues.put("tech", tech.getText().toString());
        insertValues.put("prg_desc", desc.getText().toString());
        long rows =db.insert(table, null, insertValues);
        System.out.println(rows);
        //Permission is being asked
        }
    }
}
