package com.carvindustries.prayerjournal;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewEdit extends AppCompatActivity {
    private DataBase prayers;
    private String result;
    private EditText userInput;
    private PrayerEntry editItem;
    private ArrayAdapter<PrayerEntry> prayerlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit);
        prayers = new DataBase(this);
        addListView();
    }
    private void addListView() {
        List<PrayerEntry> plistPassedIn = prayers.getAllPrayer();
        //this needs som work
        prayerlist = new ArrayAdapter<PrayerEntry>(this, android.R.layout.simple_list_item_1,plistPassedIn);
        ListView plist = (ListView)findViewById(R.id.prayerlist);
        plist.setAdapter(prayerlist);

        plist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                result = "";
                editItem = (PrayerEntry) parent.getItemAtPosition(position);
                Log.d("Rm", editItem.toString());
                LayoutInflater dlgSv = LayoutInflater.from(ViewEdit.this);
                View dialogSaveView = dlgSv.inflate(R.layout.edit_prayer, null);
                AlertDialog.Builder savePrayerBuilder = new AlertDialog.Builder(ViewEdit.this);

                savePrayerBuilder.setView(dialogSaveView);

                userInput = (EditText)dialogSaveView.findViewById(R.id.saveDialogEntry);


                savePrayerBuilder
                        .setCancelable(true)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener(){

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Log.d("RM", userInput.getText().toString());
                                        result = userInput.getText().toString();
                                        Log.d("RM",result);
                                        editItem.setText(editItem.getText() + " " + result);
                                        prayers.editPrayerEntry(editItem.getPid(), editItem.getText());
                                        prayerlist.notifyDataSetChanged();
                                    }

                                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog showEditor = savePrayerBuilder.create();
                showEditor.show();


            }
        });
    }


}
