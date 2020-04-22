package com.hritik.resume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button create;
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
        tol=findViewById(R.id.toolbar2);
        setSupportActionBar(tol);
    }
}
