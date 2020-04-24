package com.hritik.resume.main_section;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
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
                Intent ct=new Intent(MainActivity.this,MainResume.class);
                startActivity(ct);
            }
        });
        reset=findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this,R.style.AlertDialog);
                builder1.setTitle("Reset Resume");
                builder1.setMessage("Saved Information will be deleted and can not be undone.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "RESET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MyHelper hp=new MyHelper(getApplicationContext());
                                hp.onDeleteAllTables();
                                hp.close();
                                Toast.makeText(getApplicationContext(),"Reset Successful",Toast.LENGTH_LONG).show();
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        tol=findViewById(R.id.toolbar2);
        setSupportActionBar(tol);
    }
}
