package com.hritik.resume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selection extends AppCompatActivity {
    Button temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        CardView card_view = findViewById(R.id.tempcard1); // creating a CardView and assigning a value.

        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ct = new Intent(getApplicationContext(), Display.class);
                startActivity(ct);
            }
        });
    }
}
