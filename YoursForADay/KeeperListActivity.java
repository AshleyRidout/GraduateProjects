package com.example.ashleyridout.yoursforaday;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class KeeperListActivity extends AppCompatActivity {
    private static final String TAG = "KeeperList";

    EditText keeperName;
    EditText keeperPrice;
    EditText keeperQuantity;

    /**
     * @intent show layout for activity_keeper_list
     * @postconditions activity_keeper_list layout on screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keeper_list);
        Log.i(TAG, "onCreate");

        keeperName = (EditText) findViewById(R.id.etItemName);
        keeperPrice = (EditText) findViewById(R.id.etItemPrice);
        keeperQuantity = (EditText) findViewById(R.id.etItemQuantity);
    }

    /**
     * @preconditions activity_keeper_list layout on screen
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
                                        Intent intent = new Intent(KeeperListActivity.this, LoginActivity.class);
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

    //toast method to show message
    private void toast(String aToast) {
        Toast.makeText(getApplicationContext(), aToast, Toast.LENGTH_LONG).show();
    }

    /**
     * @intent create new keeper item in the database when the "Rent This Item" button is clicked
     * @preconditions the activity_keeper_list layout is created
     * @postconditions there is a new row in the keeper items table
     */
    public void newKeeperItem(View view) {
        KeeperDBHelper dbHelper = new KeeperDBHelper(this);

        int id = 0;
        String name = keeperName.getText().toString();
        double price =
                Double.parseDouble(keeperPrice.getText().toString());
        int quantity =
                Integer.parseInt(keeperQuantity.getText().toString());

        KeeperItem kItem =
                new KeeperItem(id, name, price, quantity);
        dbHelper.addKeeperItem(kItem);

        toast(kItem.getName() + " added to database.");

        //set edit texts back to blank
       // keeperID.setText("");
        keeperName.setText("");
        keeperPrice.setText("");
        keeperQuantity.setText("");
    }
}
