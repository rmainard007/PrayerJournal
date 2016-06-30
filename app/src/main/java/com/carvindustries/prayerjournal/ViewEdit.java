package com.carvindustries.prayerjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewEdit extends AppCompatActivity {
    DataBase prayers;
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
        ArrayAdapter<PrayerEntry> prayerlist = new ArrayAdapter<PrayerEntry>(this, android.R.layout.simple_list_item_1,plistPassedIn);
        ListView plist = (ListView)findViewById(R.id.prayerlist);
        plist.setAdapter(prayerlist);

        plist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // call a sub activity that allows the edting of the text then saves to the db
                // with a new date but the same id.
            }
        });
    }

}
