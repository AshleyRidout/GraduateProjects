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
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class AccountActivity extends AppCompatActivity {
    private static final String TAG = "AccountActivity";
    /**
     * @intent show layout for activity_account
     * @postconditions activity_account layout on screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Log.i(TAG, "onCreate");
    }

    /**
     * @preconditions activity_account layout on screen
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
                                        Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
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
     *
     * @preconditions activity_account layout on screen
     * @intent select FragmentOne or FragmentTwo
     * @postconditions FragmentOne or FragmentTwo on screen
     */
    public void selectFrag(View view) {
        Fragment frag;
        if (view == findViewById(R.id.button2)) {
            frag = new FragmentTwo();
        } else {
            frag = new FragmentOne();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, frag);
        fragmentTransaction.commit();
    }
}
