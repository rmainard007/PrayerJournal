package com.carvindustries.prayerjournal;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME="prayerJournal.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addSavedEntryButton();
        addEditEntryButton();
    }

    public void addSavedEntryButton(){
        Button saveEntry = (Button)findViewById(R.id.saveEntry);
        if (saveEntry != null) {
            saveEntry.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Date now = Calendar.getInstance().getTime();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
                    EditText newEnt = (EditText)findViewById(R.id.NewEntry);
                    String saveEnt = dateFormat.format(now)+ newEnt.getText().toString();
                    try {
                                               

                        FileOutputStream addEntry = openFileOutput(FILENAME, MODE_PRIVATE);
                        addEntry.write(saveEnt.getBytes());
                        addEntry.close();
                    } catch (IOException e) {
                        Log.d("RM","Cannot Write To File");
                    } catch(Exception e){
                        Log.d("RM", "Oops, there was file error");
                    }

                }
            });
        }
    }
    public void addEditEntryButton(){
        Button editEntry = (Button)findViewById(R.id.editEntry);
        editEntry.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }
}
