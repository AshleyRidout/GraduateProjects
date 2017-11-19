package com.example.ashleyridout.yoursforaday;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Ashley.Ridout on 10/1/2017.
 */

public class RegistrationReceiver extends BroadcastReceiver {
    /**
     * @intent receive broadcast and display message
     * @postconditions broadcast send and message "Registration broadcasted." displayed.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Registration broadcasted.", Toast.LENGTH_LONG).show();
    }
}
