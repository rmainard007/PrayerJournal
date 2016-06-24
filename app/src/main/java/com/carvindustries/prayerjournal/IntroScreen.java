package com.carvindustries.prayerjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        addNewEntryButton();
        addEditButton();
    }

    public void addNewEntryButton(){
        Button addEButton = (Button)findViewById(R.id.addEntry1);
        addEButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent viewAdd = new Intent(IntroScreen.this, SaveEntry.class);
                startActivity(viewAdd);
            }
        });
    }

    public void addEditButton(){
        Button editEntry = (Button)findViewById(R.id.editEntry1);
        editEntry.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent viewEdit = new Intent(IntroScreen.this, ViewEdit.class);
                startActivity(viewEdit);

            }
        });
    }
}
