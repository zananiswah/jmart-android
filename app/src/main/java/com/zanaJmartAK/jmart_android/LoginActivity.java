package com.zanaJmartAK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zanaJmartAK.jmart_android.model.Account;
import com.zanaJmartAK.jmart_android.request.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is handle the activity when user do log in
 * @author Zana Niswah Awahita
 */

public class LoginActivity extends AppCompatActivity {
    private static final Gson gson = new Gson();
    public static Account loggedAccount;

    public static Account getLoggedAccount(){
        return loggedAccount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textregister = findViewById(R.id.textView);
        textregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        EditText textemail = findViewById(R.id.login_email);
        EditText textpassword = findViewById(R.id.login_password);
        Button buttonlogin = findViewById(R.id.button2);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if(object != null){
                                Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                loggedAccount = gson.fromJson(object.toString(), Account.class);
                                startActivity(intent);
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Login Error!", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(textemail.getText().toString(), textpassword.getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(loginRequest);
            }
        });
    }
}