package com.example.ashleyridout.yoursforaday;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//class to create RegisterRequest to send user info to an outside database
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://yfad.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String user_name, String password, String email, int phone,
                           Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("user_name", user_name);
        params.put("password", password);
        params.put("email", email);
        params.put("phone", phone + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
