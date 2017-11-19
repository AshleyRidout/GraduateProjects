package com.example.ashleyridout.yoursforaday;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "Register";

    /**
     * @intent show layout for activity_register
     * @postconditions activity_register layout on screen
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.i(TAG, "onCreate");

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUserName = (EditText) findViewById(R.id.etUserName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPhone = (EditText) findViewById(R.id.etPhone);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        //processes implemented when the Register button is clicked
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                /**
                 * @intent broadcast that a new user has registered. Future implementation will include the broadcast
                 * sending a message to the YFAD server to send a welcome message to the user's email with information
                 * on confirming their email address. Since these future processes may take some time, a new thread is created.
                 * @preconditions activity_register layout is displayed
                 * @postconditions broadcast sent
                 */
                Runnable runnable = new Runnable() {
                    public void run() {
                        Intent intent = new Intent();
                        intent.setAction("com.YFAD.CUSTOM_INTENT");
                        sendBroadcast(intent);
                    }
                };
                Thread mythread = new Thread(runnable);
                mythread.start();

                /**
                 * @intent send registration info to remote database
                 * @preconditions user inputs info in all fields
                 * @postconditions user registration info sent to database
                 */
                final String name = etName.getText().toString();
                final String user_name = etUserName.getText().toString();
                final String password = etPassword.getText().toString();
                final String email = etEmail.getText().toString();
                final int phone = Integer.parseInt(etPhone.getText().toString());
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration failed.")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException j) {
                            j.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(name, user_name, password, email, phone, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
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
