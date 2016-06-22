package com.carvindustries.prayerjournal;


import android.app.Activity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by rmainard on 6/22/2016.
 */
public class EntrySaver implements View.OnClickListener {
    private DataBase Prayers;
    private Activity activity;

    public EntrySaver(DataBase prayers, Activity act) {
        Prayers = prayers;
        activity = act;
    }

    @Override
    public void onClick(View v){
        EditText newEnt = (EditText)activity.findViewById(R.id.NewEntry);
        String saveEnt = newEnt.getText().toString();
        // I need to create a separate class that extends/implements OncClickListener
        // then I can just instantiate and call an object of that type.
        //AlertDialog.Builder svdlg = new AlertDialog.Builder(this);
        //svdlg.setMessage("Saving Prayer");
        Prayers.addPrayer(saveEnt);

    }
}
