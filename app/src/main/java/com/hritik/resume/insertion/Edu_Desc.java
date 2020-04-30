package com.hritik.resume.insertion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hritik.resume.MyHelper;
import com.hritik.resume.R;
import com.hritik.resume.main_section.MainResume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Edu_Desc extends AppCompatActivity {

    String nam,cit,deg2,yr,tp,gd;
    TextView tt;
    EditText name,city,deg,from,to,cgpt1,cgpt2;
    Spinner sp;
    Button edu;

    private Boolean isValid(){

        if (Pattern.matches("[0-9]+",from.getText().toString())!=true || (Pattern.matches("[0-9]+",to.getText().toString())!=true && to.getText().toString().equalsIgnoreCase("present")!=true) ){
            Toast.makeText(getApplicationContext(),"Year is invalid in either section.",Toast.LENGTH_LONG).show();
            return false;}

        else{
            if(Integer.parseInt(to.getText().toString())<Integer.parseInt(from.getText().toString())){
                Toast.makeText(getApplicationContext(),"Invalid year in working period.",Toast.LENGTH_LONG).show();
                return false;
            }}

        /*
        if (Pattern.matches("[A-Za-z0-9.,'()\\s]+$",name.getText().toString())!=true){
            Toast.makeText(getApplicationContext(),"Enter institution name properly.",Toast.LENGTH_LONG).show();
            return false;
        }


        if (Pattern.matches("[A-Za-z.()\\s]+",city.getText().toString())!=true){
            Toast.makeText(getApplicationContext(),"Enter city name properly.",Toast.LENGTH_LONG).show();
            return false;
        }
         */
        if (sp.getSelectedItem().toString().equalsIgnoreCase("percentage")){
        if ((int)(Double.parseDouble(cgpt1.getText().toString().replaceAll("%","")))>100 && sp.getSelectedItem().toString().equalsIgnoreCase("percentage")){
            Toast.makeText(getApplicationContext(),"Enter percentage properly.",Toast.LENGTH_LONG).show();
            return false;
        }}
        if (sp.getSelectedItem().toString().equalsIgnoreCase("cgpa")){
        if (Double.parseDouble(cgpt1.getText().toString())>Double.parseDouble(cgpt2.getText().toString()) && sp.getSelectedItem().toString().equalsIgnoreCase("cgpa")){
            Toast.makeText(getApplicationContext(),"Enter cgpa properly.",Toast.LENGTH_LONG).show();
            return false;
        }}
        return true;
    }
    protected boolean getLengthStatus(){
        ArrayList<EditText> arr =new ArrayList<EditText>(Arrays.asList(name,city,deg,from,to,cgpt1));
        for(int i=0;i<arr.size();i++) {
            if (arr.get(i).getText().length() < 1) {
                return false;
            }
        }
        return true;
    }
    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu__desc);
        edu = findViewById(R.id.edudone_btn);
        name=findViewById(R.id.edu_name);
        city=findViewById(R.id.edu_city);
        deg=findViewById(R.id.edu_deg);
        from=findViewById(R.id.edu_from);
        to=findViewById(R.id.edu_to);
        sp=findViewById(R.id.edu_spn);
        cgpt1=findViewById(R.id.edu_cgpted);
        cgpt2=findViewById(R.id.edu_outcg);
        tt=findViewById(R.id.txtcg);
        tt.setVisibility(View.INVISIBLE);
        cgpt2.setVisibility(View.INVISIBLE);

        ArrayList<String> categories = new ArrayList<String>();
        categories.add("Percentage");
        categories.add("CGPA");
        Intent intt=getIntent();
        Bundle bd=intt.getExtras();
        if (bd!=null){
            nam=bd.getString("name");
            cit=bd.getString("city");
            deg2=bd.getString("deg");
            yr=bd.getString("year");
            tp=bd.getString("tp");
            gd=bd.getString("gd");
            String[] tofr=yr.split("-");
            //Toast.makeText(getApplicationContext(),"Update",Toast.LENGTH_LONG).show();
            name.setText(nam);
            city.setText(cit);
            deg.setText(deg2);
            from.setText(tofr[0].trim());
            to.setText(tofr[1].trim());
            if (tp.equalsIgnoreCase("percentage")){cgpt1.setText(gd);}
           else{String[] cgp=gd.split("/");cgpt1.setText(cgp[0]);cgpt2.setText(cgp[1]);}
            sp.setSelection(getIndex(sp, tp));
            edu.setText("Update");
            }
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sp.setAdapter(dataAdapter);
        if (tp!=null){sp.setSelection(getIndex(sp, tp));}
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (sp.getSelectedItem().toString().equalsIgnoreCase("cgpa")){
                    tt.setVisibility(View.VISIBLE);
                    cgpt2.setVisibility(View.VISIBLE);
                }
                else{
                    tt.setVisibility(View.INVISIBLE);
                    cgpt2.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getLengthStatus()){
                    if (isValid()){
                    register("edu");
                    Intent ct=new Intent(getApplicationContext(), MainResume.class);
                    ct.putExtra("num","4");
                    startActivity(ct);
                }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Details are not filled",Toast.LENGTH_LONG).show();}
            }
        });
    }
    private void register(String table){
        MyHelper dpHelper = new MyHelper(this);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        String type=sp.getSelectedItem().toString();
        String per=cgpt1.getText().toString();
        String from1=from.getText().toString();
        String to1=to.getText().toString();
        if(nam!=null){
            if (type.equalsIgnoreCase("cgpa")){per+="/"+cgpt2.getText().toString();}
            if (type.equalsIgnoreCase("percentage")){per.replaceAll("%","");per+="%";}
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name.getText().toString());
            contentValues.put("city", city.getText().toString());
            contentValues.put("degree", deg.getText().toString());
            contentValues.put("tfrom", from1);
            contentValues.put("tto", to1);
            contentValues.put("type", type);
            contentValues.put("grade", per);
            String whereClause = "name =?";
            String whereArgs[] = {nam};
            db.update(table, contentValues, whereClause, whereArgs);
            db.close();
        }
        else{
        if (type.equalsIgnoreCase("percentage")){per+="%";}
        else if (type.equalsIgnoreCase("cgpa")){per+="/"+cgpt2.getText().toString();}
        ContentValues insertValues = new ContentValues();
        insertValues.put("name", name.getText().toString());
        insertValues.put("city", city.getText().toString());
        insertValues.put("degree", deg.getText().toString());
        insertValues.put("tfrom", from1);
        insertValues.put("tto", to1);
        insertValues.put("type", type);
        insertValues.put("grade", per);
        long rows =db.insert(table, null, insertValues);
        System.out.println(rows);
        //Permission is being asked
        }
    }
}
