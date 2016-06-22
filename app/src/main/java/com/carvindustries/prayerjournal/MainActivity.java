package com.carvindustries.prayerjournal;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //public static final String FILENAME="prayerJournal.txt";
    private DataBase Prayers = new DataBase(this);
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
                    EditText newEnt = (EditText)findViewById(R.id.NewEntry);
                    String saveEnt = newEnt.getText().toString();
                    // I need to create a separate class that extends/implements OncClickListener
                    // then I can just instantiate and call an object of that type.
                    //AlertDialog.Builder svdlg = new AlertDialog.Builder(this);
                    //svdlg.setMessage("Saving Prayer");
                    Prayers.addPrayer(saveEnt);

                    /*
                    try {
                        FileOutputStream addEntry = openFileOutput(FILENAME, MODE_PRIVATE);
                        addEntry.write(saveEnt.getBytes());
                        addEntry.close();
                    } catch (IOException e) {
                        Log.d("RM","Cannot Write To File");
                    } catch(Exception e){
                        Log.d("RM", "Oops, there was file error");
                    }
                    */
                }
            });
        }
    }
    public void addEditEntryButton(){
        Button editEntry = (Button)findViewById(R.id.editEntry);
        editEntry.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                List<String> lp = Prayers.getAllPrayer();
                Log.d("rm", String.valueOf(lp.size()));
                for(String value: lp){
                    Log.d("RM ", value);
                }

            }
        });
    }
}
