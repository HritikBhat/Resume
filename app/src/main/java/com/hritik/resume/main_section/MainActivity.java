package com.hritik.resume.main_section;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hritik.resume.MyHelper;
import com.hritik.resume.R;

public class MainActivity extends AppCompatActivity {
    AppCompatButton create,reset;
    Toolbar tol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create=findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ct=new Intent(getApplicationContext(),MainResume.class);
                startActivity(ct);
            }
        });
        reset=findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Here Dialogue should come for verification purpose
                Toast.makeText(getApplicationContext(),"Resume got reset!",Toast.LENGTH_LONG).show();
                MyHelper hp=new MyHelper(getApplicationContext());
                hp.onDeleteAllTables();
                hp.close();
            }
        });
        tol=findViewById(R.id.toolbar2);
        setSupportActionBar(tol);
    }
}
