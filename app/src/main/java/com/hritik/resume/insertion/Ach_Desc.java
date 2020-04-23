package com.hritik.resume.insertion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hritik.resume.MyHelper;
import com.hritik.resume.R;
import com.hritik.resume.main_section.MainResume;

public class Ach_Desc extends AppCompatActivity {

    EditText ed;
    Button achh;
    String des;
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
        des=bd.getString("des");
        tt=findViewById(R.id.txt1);
        if(des!=null){
            //Toast.makeText(getApplicationContext(),"Update",Toast.LENGTH_LONG).show();
            ed.setText(des);
            achh.setText("Update");
        }
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
                    Intent ct=new Intent(getApplicationContext(), MainResume.class);
                    ct.putExtra("num",no);
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
            String desc=ed.getText().toString();
            contentValues.put(table + "_desc", desc);
            String whereClause = table + "_desc =?";
            String whereArgs[] = {des};
            db.update(table, contentValues, whereClause, whereArgs);
            /*
            String query="Update "+table+" SET "+table + "_desc = '"+ed.getText().toString()+"' WHERE "+table + "_desc='"+des+"'";
            db.execSQL(query);
            */
            db.close();

        }
        else {
            ContentValues insertValues = new ContentValues();
            insertValues.put(table + "_desc", ed.getText().toString());
            long rows = db.insert(table, null, insertValues);
            System.out.println(rows);
            db.close();
        }
        dpHelper.close();
        //Permission is being asked
    }
}
