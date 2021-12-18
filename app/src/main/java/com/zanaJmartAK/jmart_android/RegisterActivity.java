package com.zanaJmartAK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zanaJmartAK.jmart_android.model.Account;
import com.zanaJmartAK.jmart_android.request.RegisterRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is handle the activity when user do register new account
 * @author Zana Niswah Awahita
 */

public class RegisterActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    private static Account loggedAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText textname = findViewById(R.id.editTextTextPersonName);
        EditText textemail = findViewById(R.id.editTextTextEmailAddress2);
        EditText textpassword = findViewById(R.id.editTextTextPassword2);
        Button buttonregister = findViewById(R.id.button);
        buttonregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            Toast.makeText(RegisterActivity.this, "Register Success!", Toast.LENGTH_SHORT);
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            loggedAccount = gson.fromJson(object.toString(), Account.class);
                            startActivity(intent);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Register Error!", Toast.LENGTH_SHORT);
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(textname.getText().toString(), textemail.getText().toString(), textpassword.getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(registerRequest);

            }
        });
    }
}