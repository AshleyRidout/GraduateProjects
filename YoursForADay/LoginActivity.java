package com.example.ashleyridout.yoursforaday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "Login";
    /**
     *
     * @intent show layout for activity_login
     * @postconditions activity_login layout on screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Log.i(TAG, "onCreate");

        final EditText etUserName = (EditText) findViewById(R.id.etUserName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegister);

        //set "Register" as link to RegisterActivity class
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
                Log.i(TAG, "registerLinkClicked");
            }
        });

        //set "LOGIN" button as link to WelcomeUserActivity class
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View l){
                Intent loginIntent = new Intent(LoginActivity.this, WelcomeUserActivity.class);
                LoginActivity.this.startActivity(loginIntent);
                Log.i(TAG, "loginButtonClicked");

                /**
                 *
                 * @intent send username to WelcomeUserActivity
                 * @preconditions the activity_login layout is created
                 * @postconditions the username is sent to WelcomeUserActivity
                 */
                final EditText UserName = (EditText) findViewById(R.id.etUserName);

                String userName = (UserName.getText().toString());
                loginIntent.putExtra("nameString", userName);
                startActivity(loginIntent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }
}
