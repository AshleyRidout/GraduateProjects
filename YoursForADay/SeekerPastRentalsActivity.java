package com.example.ashleyridout.yoursforaday;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeekerPastRentalsActivity extends ActionBarListActivity {
    private static final String TAG = "SeekerPastRentals";
    ArrayList<String> pastRentalList = new ArrayList<String>();
    ArrayAdapter<String> adaptPast;
    List<String> checkList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_past_rentals);
        Log.i(TAG, "onCreate");

        /**
         *
         * @intent get items chosen by user to checkout from CheckoutActivity
         * @postconditions id, name and price of item(s) chosen to rent displayed
         */
        Bundle extra = getIntent().getExtras();
        if (extra == null) {
            return;
        }
        String pastRentalString = extra.getString("pastRentalString");
        pastRentalList.add(pastRentalString);

        String temp = pastRentalList.toString();
        temp = temp.replace("[", "").replace("]", "");

        checkList = Arrays.asList(temp.split(", "));

        adaptPast = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, checkList);
        setListAdapter(adaptPast);
    }
    /**
     * @preconditions activity_user_welcome layout on screen
     * @intent show layout for welcome_menu
     * @postconditions welcome_menu layout on screen
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflates the menu
        getMenuInflater().inflate(R.menu.welcome_menu, menu);
        return true;
    }

    /**
     * @return appropriate menu choice
     * @preconditions welcome_menu layout on screen
     * @intent show welcome_menu choice
     * @postconditions either the appropriate class is opened, or if not yet implemented, a message is displayed
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.welcome:
                Intent welcomeIntent = new Intent(this, WelcomeUserActivity.class);
                this.startActivity(welcomeIntent);
                break;
            case R.id.account:
                Intent intent = new Intent(this, AccountActivity.class);
                this.startActivity(intent);
                break;
            case R.id.keeperListNewItems:
                Intent keepListIntent = new Intent(this, KeeperListActivity.class);
                this.startActivity(keepListIntent);
                break;
            case R.id.keeperCurrentItems:
                Intent keeperCurrentIntent = new Intent(this, KeeperCurrentActivity.class);
                this.startActivity(keeperCurrentIntent);
                break;
            case R.id.seekerFindItems:
                Intent seekItemIntent = new Intent(this, SeekerFindActivity.class);
                this.startActivity(seekItemIntent);
                break;
            case R.id.pastRentals:
                Intent pastIntent = new Intent(this, CheckoutActivity.class);
                this.startActivity(pastIntent);
                break;
            case R.id.logout:
                // alertDialogBuilder built to implement postcondition
                AlertDialog.Builder alertDialogBuilder =
                        new AlertDialog.Builder(this);
                alertDialogBuilder
                        .setTitle("Thank you for visiting YFAD.")
                        .setMessage("Are you sure you want to log out?")
                        .setCancelable(false) // avoid indeterminate state
                        .setNegativeButton("Return",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();  // close dialog box only
                                    }
                                }
                        )
                        .setPositiveButton("Logout",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        Intent intent = new Intent(SeekerPastRentalsActivity.this, LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                        );

                // Postcondition
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * @intent get listViewId that will hold list data
     * @postconditions listViewId is obtainable
     * @return int of listViewId
     */
    @Override
    protected int getListViewId() {
        return R.id.listPast;
    }
}
