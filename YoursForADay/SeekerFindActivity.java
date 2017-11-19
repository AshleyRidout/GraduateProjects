package com.example.ashleyridout.yoursforaday;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SeekerFindActivity extends ActionBarListActivity {
    private static final String TAG = "SeekFindActivity";
    private SQLiteDatabase DB;
    private ArrayList<String> items = new ArrayList<String>();
    ArrayList<String> checkout = new ArrayList<String>();

    EditText seekName;
    EditText seekPrice;

    /**
     * @intent show layout for activity_seeker_find
     * @postconditions activity_keeper_seeker_find layout on screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker_find);
        Log.i(TAG, "onCreate");

        seekName = (EditText) findViewById(R.id.etSeekName);
        seekPrice = (EditText) findViewById(R.id.etSeekPrice);
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
                                        Intent intent = new Intent(SeekerFindActivity.this, LoginActivity.class);
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
     * @preconditions user inputs item name and price
     * @intent find items with this item name at or below the user inputted price
     * @postconditions listView of items that match
     */
    public void findThisItem(View view) {
        KeeperDBHelper dbHelper = new KeeperDBHelper(this);

        items = dbHelper.findKeeperItem(seekName.getText().toString(), Double.parseDouble(seekPrice.getText().toString()));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);

        if(items.isEmpty()){
                Toast.makeText(this, "No match found for " + seekName.getText() + " at this price.",Toast.LENGTH_LONG).show();
        }
        seekName.setText("");
        seekPrice.setText("");
    }

    /**
     * @preconditions items in listView
     * @intent send item to Checkout
     * @postconditions item is sent to CheckoutActivity
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.d("click", "Position click " + position);
        String item = (String) getListAdapter().getItem(position);
        checkout.add(item);

        String items = checkout.toString();
        Intent i = new Intent(this, CheckoutActivity.class);

        i.putExtra("iString", items);
        startActivity(i);
        Toast.makeText(this, item + " sent to checkout.", Toast.LENGTH_LONG).show();
    }

    @Override
    protected int getListViewId() {
        return R.id.list;
    }

}
