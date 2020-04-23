package com.hritik.resume.main_section;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hritik.resume.R;
import com.hritik.resume.displays.Display;
import com.hritik.resume.displays.Display2;

public class Selection extends AppCompatActivity {
    Button temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        try{
        CardView card_view1 = findViewById(R.id.tempcard1);
        CardView card_view2 = findViewById(R.id.tempcard2);// creating a CardView and assigning a value.

        card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ct = new Intent(getApplicationContext(), Display.class);
                startActivity(ct);
            }
        });
        card_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ct = new Intent(getApplicationContext(), Display2.class);
                startActivity(ct);
            }
        });}
        catch (Exception e){e.printStackTrace();}
    }
}
