package com.carvindustries.prayerjournal;

import android.content.SharedPreferences;
import android.os.AsyncTask;
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
    private DataBase Prayers =new DataBase(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addSavedEntryButton();
        addEditEntryButton();

    }

    public void addSavedEntryButton(){
        final Button saveEntry = (Button)findViewById(R.id.saveEntry);
        EntrySaver entrySav = new EntrySaver(Prayers, this);
        if (saveEntry != null) {
            AlertDialog.Builder buildDlg = new AlertDialog.Builder(this);
            buildDlg.setMessage("Saving Prayer");
            AlertDialog svdlg = buildDlg.create();

            svdlg.show();
            saveEntry.setOnClickListener(entrySav);

            svdlg.dismiss();
        }
    }
    public void addEditEntryButton(){
        Button editEntry = (Button)findViewById(R.id.editEntry);
        editEntry.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                List<String> lp = Prayers.getAllPrayer();

                for(String value: lp){
                    Log.d("RM ", value);
                }

            }
        });
    }
}
