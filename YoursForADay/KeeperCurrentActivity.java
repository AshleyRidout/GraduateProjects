package com.example.ashleyridout.yoursforaday;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.database.Cursor;

import java.util.ArrayList;
import android.widget.ArrayAdapter;

public class KeeperCurrentActivity extends ActionBarListActivity {
    private static final String TAG = "KeeperCurrentRental";
    private SQLiteDatabase newDB;
    private ArrayList<String> results = new ArrayList<String>();

    /**
     * @intent show layout for activity_keeper_current
     * @postconditions activity_keeper_current layout on screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keeper_current);
        Log.i(TAG, "onCreate");

      openAndQueryDatabase();
        displayResultList();
    }

        /**
         * @preconditions activity_keeper_current layout on screen
         * @intent show layout for welcome_menu
         * @postconditions welcome_menu layout on screen
         */
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflates the menu
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.welcome_menu, menu);
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
                                            Intent intent = new Intent(KeeperCurrentActivity.this, LoginActivity.class);
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
     * @intent find items in database that match the item name and are below the asking price
     * @postconditions cursor of items that match
     */
    private void openAndQueryDatabase() {
        try {
            KeeperDBHelper dbHelper = new KeeperDBHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT _id, itemName, itemPrice FROM " +
                    KeeperDBHelper.KEEPER_ITEM_TABLE_NAME +
                    " where itemQuantity >= 1", null);

            if (c != null ) {
                if  (c.moveToFirst()) {
                    do {
                        int id = c.getInt(c.getColumnIndex("_id"));
                        String name = c.getString(c.getColumnIndex("itemName"));
                        double price = c.getDouble(c.getColumnIndex("itemPrice"));
                        results.add("ID: " + id + "   Name: " + name + "   Price: $" + price);
                    }while (c.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or open the database");
            newDB.close();
        }
    }

    /**
     * @intent display results of querying database for matching items
     * @postconditions items are displayed as a list
     */
    private void displayResultList() {
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        getListView().setTextFilterEnabled(true);
    }

    /**
     * @intent get listViewId that will hold list data
     * @postconditions listViewId is obtainable
     * @return int of listViewId
     */
    @Override
    protected int getListViewId() {
        return R.id.listCurrent;
    }
}
